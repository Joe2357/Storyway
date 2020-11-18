#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define max(a, b) (((a) > (b)) ? (a) : (b))
#define min(a, b) (((a) > (b)) ? (b) : (a))

#define MAX_INDEX 30
typedef char boolean;
#define True 1
#define False 0;
typedef struct Position {
	int num;
	char name[10];
	int stay_time;
	int priority;
} PS;
typedef struct Result {
	int choose[MAX_INDEX];
	int choose_length;
	int time_used, previous_time;
	int visited;
} RS;

PS vertax[MAX_INDEX]; int vertax_count;
RS retval[MAX_INDEX]; int retval_length;
int matrix[MAX_INDEX][MAX_INDEX];
int duration;

void init() {
	#define INF 987654321
	for (int i = 0; i < MAX_INDEX; ++i)
		for (int j = 0; j < MAX_INDEX; ++j)
			matrix[i][j] = INF * (i != j);
	return;
}
boolean sample_input() {
	/* 파일 열기 */
	FILE* input = fopen("sample_input.txt", "r");
	if (!input) {
		printf("Error\n");
		return False;
	}

	/* 정점 정보 입력받기 */
	fscanf(input, "%d", &vertax_count);
	for (int i = 0; i < vertax_count; ++i) {
		char n[10];
		fscanf(input, "%s %d", n, &vertax[i].stay_time);
		vertax[i].num = i, vertax[i].priority = 0;
		strcpy(vertax[i].name, n);
	}

	/* 간선 정보 입력받기 */
	int line;
	fscanf(input, "%d", &line);
	for (int i = 0; i < line; ++i) {
		int a, b, c;
		fscanf(input, "%d %d %d", &a, &b, &c);
		matrix[a][b] = c, matrix[b][a] = c;
	}

	/* 시간 입력받기 */
	fscanf(input, "%d", &duration);

	fclose(input);
	return True;
}
void floyd_warshall() {
	for (int i = 0; i < vertax_count; ++i)
		for (int j = 0; j < vertax_count; ++j)
			for (int k = 0; k < vertax_count; ++k)
				matrix[j][k] = min(matrix[j][k], matrix[j][i] + matrix[i][k]);
	return;
}

void debug() {
	FILE* output = fopen("output.txt", "w");
	for (int i = 0; i < vertax_count; ++i) {
		for (int j = 0; j < vertax_count; ++j)
			fprintf(output, "%3d ", matrix[i][j]);
		fprintf(output, "\n");
	}
	return;
}
void print_result() {
	for (int i = 0; i < retval_length; ++i) {
		printf(
			"case %d\n"
			"    time used : %d\n"
			"    travel path : "
			, i, retval[i].previous_time);
		for (int j = 0; j <= retval[i].choose_length; ++j)
			printf("%s -> ", vertax[retval[i].choose[j]].name);
		printf("\n");
	}
	return;
}
void output_result() {
	FILE* output = fopen("output.txt", "w");
	for (int i = 0; i < retval_length; ++i) {
		fprintf(output,
			"case %d\n"
			"    time used : %d\n"
			"    travel path : "
			, i, retval[i].previous_time);
		for (int j = 0; j <= retval[i].choose_length; ++j)
			fprintf(output, "%s -> ", vertax[retval[i].choose[j]].name);
		fprintf(output, "\n");
	}
	fclose(output);
	return;
}

void add_to_result(RS node) {
	boolean isNotInserted = True;

	/* 순회하면서 검사 */
	for (int i = 0; i < retval_length && isNotInserted; ++i) {

		/* 순회했던 경로가 같은지 검사 */
		if (retval[i].visited == node.visited) {
			isNotInserted = False;

			/* 더 최적의 경로를 대신 넣음 */
			if ((retval[i].previous_time > node.previous_time) ||
				((retval[i].previous_time == node.previous_time) && (retval[i].choose_length < node.choose_length)))
				retval[i] = node;
		}
	}

	/* 값이 추가되지 않았었다면 넣어줘야함 */
	if (isNotInserted)
		retval[retval_length++] = node;
	return;
}
void dfs(RS node) {
	/* 경로를 더 추가할 수 있는지에 대한 여부 */
	boolean isThereMorePath = False;

	/* 경로를 대입해보자 (dfs) */
	node.previous_time = node.time_used;
	for (int i = 0; i < vertax_count; ++i) {
		/* 이미 방문한 곳인지 검사 */
		if ((node.visited & (1 << i)) == 0) {

			/* 임시변수 생성 */
			int previous_vertax = node.choose[node.choose_length]; /* 현재 경로가 도달한 마지막 지점 */
			int temp_time = node.time_used + matrix[previous_vertax][i] + vertax[i].stay_time; /* 만약 노드에 간다면 사용하게 될 시간 */

			/* 노드를 추가하여도 가능한지 체크 */
			if (temp_time <= duration) {
				node.time_used += (matrix[previous_vertax][i] + vertax[i].stay_time); /* 시간 추가 */
				node.choose[++node.choose_length] = i; /* 경로 추가 */
				node.visited |= (1 << i); /* 지나갔다는 알림 추가 */
				dfs(node); /* 업데이트된 노드로 다시 dfs */

				/* 원상태로 복귀 */
				node.time_used = node.previous_time;
				--node.choose_length;
				node.visited &= (~(1 << i));

				/* 추가 경로가 있었음을 알림 */
				isThereMorePath = True;
			}
		}
	}

	/* 시간이 지난 경우 == 업데이트된 경로가 없는 경우 */
	if (!isThereMorePath)
		add_to_result(node);

	return;
}
void solve() {
	/* 비어있는 노드 생성 */
	RS temp;
	temp.choose[0] = 0;
	temp.choose_length = 0, temp.time_used = 0, temp.visited = 1;

	/* 1안 : dfs 돌림 */
	dfs(temp);
	return;
}

int main() {
	/* 기본 정보 입력단계 */
	init();
	if (!sample_input())
		return 0;
	floyd_warshall();

	/* 경로 추적 알고리즘 실행단계 */
	solve();

	/* 결과 출력단계 */
	// print_result();
	output_result();

	return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define max(a, b) (((a) > (b)) ? (a) : (b))
#define min(a, b) (((a) > (b)) ? (b) : (a))

#define MAX_INDEX 100
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
	int time_used;
} RS;

PS node[MAX_INDEX];
RS retval[MAX_INDEX]; int retval_length;
int matrix[MAX_INDEX][MAX_INDEX];
int node_count;
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
	fscanf(input, "%d", &node_count);
	for (int i = 0; i < node_count; ++i) {
		char n[10];
		fscanf(input, "%s %d", n, &node[i].stay_time);
		node[i].num = i, node[i].priority = 0;
		strcpy(node[i].name, n);
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
	for (int i = 0; i < node_count; ++i)
		for (int j = 0; j < node_count; ++j)
			for (int k = 0; k < node_count; ++k)
				matrix[j][k] = min(matrix[j][k], matrix[j][i] + matrix[i][k]);
	return;
}

void debug() {
	FILE* output = fopen("output.txt", "w");
	for (int i = 0; i < node_count; ++i) {
		for (int j = 0; j < node_count; ++j)
			fprintf(output, "%3d ", matrix[i][j]);
		fprintf(output, "\n");
	}
	return;
}
void output_result() {
	for (int i = 0; i < retval_length; ++i) {
		printf("temp\n");
	}
	return;
}

void solve() {

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
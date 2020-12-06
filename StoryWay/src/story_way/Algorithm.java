package story_way;

import story_way.Main;
import story_way.Path;
import story_way.Restaurant;

/**
 * Class "Algorithm"
 * @author Joe2357
 */
public class Algorithm {

	/**
	 * Method "Solve"
	 * - find the best way while visiting all chosen vertexs
	 */
	public static void solve() {
		dfs(new Path());
	}

	/**
	 * Method "floydWarshall"
	 * - fill all weights of group matrix
	 */
	public static void floydWarshall() {
		for (int i = 0; i < Main.GROUPSIZE; ++i) {
			for (int j = 0; j < Main.GROUPSIZE; ++j) {
				for (int k = 0; k < Main.GROUPSIZE; ++k) {
					Main.matrix[j][k] = min(Main.matrix[j][k], Main.matrix[j][i] + Main.matrix[i][k]);
				}
			}
		}
	}

	/**
	 * Method "min"
	 * - choose smaller value of 2 integers
	 * 
	 * @param i[Integer] : number
	 * @param j[Integer] : number
	 * @return smaller value of i and j
	 */
	private static int min(int i, int j) {
		return ((i > j) ? j : i);
	}

	/**
	 * Method "dfs"
	 * - find all possible ways, and alternate the best way (in main class)
	 *  
	 * @param cur[Path] : current dfs path
	 */
	private static void dfs(Path cur) {

		/* copy path */
		Path current = new Path(cur);

		/* first, add a possible restaurant in current group to path */
		for (int i = 0; i < Main.restaurantCount[current.currentGroup]; ++i) {
			Restaurant r = Main.restaurantList[current.currentGroup][i];
			if (!current.resVisited[r.num]) { /* if not visited */
				Path temp = new Path(current);
				if (temp.isAvailable(r)) { /* if that store is in working */
					temp.addToPath(r);
				} else { /* if that store is in break */
					temp.addToPath(r);
					temp.usedTime = r.breakEnd + r.stayTime;
				}
				if (temp.isPromising()) { /* if new path is promising */
					dfs(temp);
				} else { /* end of date */
					current.changeBetter();
				}
				return;
			}
		}

		/* move to another group if other restaurant is not available */
		current.groVisited[current.currentGroup] = true;
		for (int i = 1; i < Main.GROUPSIZE; ++i) {
			if (!current.groVisited[i]) { /* if not visited */
				Path temp = new Path(current);
				temp.usedTime += Main.matrix[current.currentGroup][i];
				temp.currentGroup = i;
				if (temp.isPromising()) {
					dfs(temp);
				} else {
					current.changeBetter();
				}
				return;
			}
		}

		/* re-compare */
		if (current.isPromising()) {
			current.changeBetter();
		}
	}
}

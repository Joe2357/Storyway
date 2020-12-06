package story_way;

import story_way.Restaurant;

/**
 * Class "Main"
 * @author Joe2357
 *
 * @param restaurantList[Restaurant[group][index]] : all restaurants' information
 * @param restaurantCount[Integer[group]] : count of restaurants by group
 * @param restaurantNumber[Integer] : count of all restaurants
 * @param GROUPSIZE[Integer] : maximum group size value
 *     - constant value (default = 40)
 * @param PROGRAMSTART[Integer] : the time when date starts
 *     - constant value (default = 420)
 * @param endDate[Integer] : the time when date ends
 * @param matrix[Integer[group][group]] : adjacency matrix of group
 * @param INF[Integer] : the maximum value
 *     - constant value (default = 987654321)
 * @param willNotConsiderRes[Boolean[index]] : if true, we won't visit index's restaurant
 * @param willNotConsiderGroup[Boolean[index]] : if true, there's no restaurant we will visit
 * 
 * @param bestWay[Path] : the best way while visiting some vertexs
 */
public class Main {

	public static Restaurant[][] restaurantList;
	public static int[] restaurantCount;
	public static int restaurantNumber;
	public static final int GROUPSIZE = 40;
	public static final int PROGRAMSTART = 420; /* 7 O'clock */
	public static int endDate = 2000;
	public static int[][] matrix;
	public static final int INF = 987654321;
	public static boolean[] willNotConsiderRes;
	public static boolean[] willNotConsiderGroup;

	public static Path bestWay;

	/**
	 * Method "main"
	 * - get user input, and return the best way that this server calculate
	 * 
	 * @param userChoice[String] : the restaurants' numbers that user want to visit
	 * @return bestWay[String] : the best way that server calculates
	 */
	public static String main(String userChoice) {

		/* initialize restaurant variables */
		restaurantList = new Restaurant[GROUPSIZE][30];
		restaurantCount = new int[GROUPSIZE];
		for (int i = 0; i < GROUPSIZE; ++i) {
			restaurantCount[i] = 0;
		}

		/* initialize group variables */
		matrix = new int[GROUPSIZE][GROUPSIZE];
		for (int i = 0; i < GROUPSIZE; ++i) {
			for (int j = 0; j < GROUPSIZE; ++j) {
				matrix[i][j] = INF;
			}
		}

		/* read all restaurants & edges info */
		GetInput.readInput();
		++restaurantNumber;

		/* initialize visited variables */
		willNotConsiderRes = new boolean[restaurantNumber];
		willNotConsiderGroup = new boolean[GROUPSIZE];
		for (int i = 0; i < restaurantNumber; ++i) {
			willNotConsiderRes[i] = true;
		}

		/* read input from user */
		String[] nums = userChoice.replace(" ", "").split(",");
		for (String x : nums) {
			willNotConsiderRes[Integer.parseInt(x)] = false;
		}
		for (int i = 0; i < GROUPSIZE; ++i) {
			boolean temp = true;
			for (int j = 0; j < restaurantCount[i]; ++j) {
				Restaurant r = restaurantList[i][j];
				temp &= willNotConsiderRes[r.num];
			}
			willNotConsiderGroup[i] = temp;
		}

		/* create result object */
		bestWay = new Path();
		bestWay.usedTime = INF;

		/* calculate */
		Algorithm.floydWarshall();
		Algorithm.solve();

		/* return result */
		return bestWay.printObject();
	}
}

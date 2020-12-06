package story_way;

import java.io.*;
import java.util.Scanner;

/**
 * Class "GetInput"
 * @author Joe2357
 */
public class GetInput {

	/**
	 * Method "readInput"
	 * - read restaurants' data from file
	 */
	public static void readInput() {

		/* open new scanner */
		Scanner sc = null;
		try {
			sc = new Scanner(new File("C://Users/newInput.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		/* restaurant number */
		Main.restaurantNumber = sc.nextInt();
		sc.nextLine();

		/* read restaurants' information */
		for (int i = 1; i <= Main.restaurantNumber; ++i) {
			/* add to List automatically */
			Restaurant temp = new Restaurant(sc.nextLine());
			temp.addToList(Main.restaurantList[temp.group]);
		}

		/* edge number */
		int line = sc.nextInt();

		/* read edges' information */
		for (int i = 0; i < line; ++i) {
			int a, b, c;
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();

			Main.matrix[a][b] = c;
			Main.matrix[b][a] = c;
		}

		/* close scanner */
		sc.close();
	}
}

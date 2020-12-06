package story_way;

/**
 * Class "Restaurant"
 * @author Joe2357
 *
 * @param name[String] : restaurant's name
 * @param num[Integer] : restaurant's index
 * @param stayTiem[Integer] : time that user will stay there
 * @param breakStart[Integer] : time that this restaurant will be in break
 * @param breakEnd[Integer] : time that this restaurant will be out break
 * @param preference[Integer] : star rate of this restaurant
 * @param group[Integer] : group number that this restaurant is in
 * 
 * @param cood_x[Double] : latitude of this restaurant
 * @param cood_y[Double] : longitude of this restaurant
 */
public class Restaurant {

	public String name;
	public int num;
	public int stayTime;
	public int breakStart;
	public int breakEnd;
	public int preference;
	public int group;

	public double cood_x;
	public double cood_y;

	/* default constructor */
	public Restaurant() {
	}

	/* constructor that fill information automatically */
	public Restaurant(String line) {
		String[] splited = line.split("\"");
		this.name = splited[1];

		String[] temp = splited[0].split(" ");
		this.num = Integer.valueOf(temp[0].trim());
		this.stayTime = Integer.valueOf(temp[1].trim());

		temp = splited[2].trim().split(" ");
		this.breakStart = Integer.valueOf(temp[0]);
		this.breakEnd = Integer.valueOf(temp[1]);
		this.preference = Integer.valueOf(temp[2]);
		this.group = Integer.valueOf(temp[3]);

		this.cood_x = Double.valueOf(temp[4]);
		this.cood_y = Double.valueOf(temp[5]);
	}

	/**
	 * Method "addToList"
	 * - add this restaurant to restaurantList(main)
	 * 
	 * @param arr[Restaurant[group][index]]
	 */
	public void addToList(Restaurant[] arr) {
		arr[Main.restaurantCount[this.group]] = this;
		Main.restaurantCount[this.group] += 1;
	}

	/**
	 * @deprecated
	 * Method "printObject"
	 * - print restaurant's information
	 */
	public void printObject() {
		System.out.println(this.name + " " + this.num + " " + this.breakStart + " " + this.breakEnd);
	}
}

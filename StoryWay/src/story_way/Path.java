package story_way;

import java.util.ArrayList;
import story_way.Restaurant;

/**
 * Class "Path"
 * @author Joe2357
 * 
 * @param p[ArrayList<Restaurant>] : restaurants' list that this path visited
 * @param currentGroup[Integer] : current group that this path are in
 * @param resVisited[Boolean[index]] : restaurants that this path visited
 * @param groVisited[Boolean[index]] : groups that this path visited
 * @param usedTime[Integer] : time that this path was used
 */
public class Path {

	public ArrayList<Restaurant> p;
	public int currentGroup;
	public boolean[] resVisited;
	public boolean[] groVisited;
	public int usedTime;

	/* default constructor */
	public Path() {
		p = new ArrayList<Restaurant>();
		resVisited = new boolean[Main.restaurantNumber];
		for (int i = 0; i < Main.restaurantNumber; ++i) {
			resVisited[i] = Main.willNotConsiderRes[i];
		}
		groVisited = new boolean[Main.GROUPSIZE];
		for (int i = 0; i < Main.GROUPSIZE; ++i) {
			groVisited[i] = Main.willNotConsiderGroup[i];
		}
		usedTime = Main.PROGRAMSTART;
		currentGroup = 0;
	}

	/* copy constructor */
	public Path(Path x) {
		this.p = new ArrayList<Restaurant>();
		p.addAll(x.p);
		this.resVisited = new boolean[Main.restaurantNumber];
		for (int i = 0; i < Main.restaurantNumber; ++i) {
			this.resVisited[i] = x.resVisited[i];
		}
		groVisited = new boolean[Main.GROUPSIZE];
		for (int i = 0; i < Main.GROUPSIZE; ++i) {
			groVisited[i] = x.groVisited[i];
		}
		this.usedTime = x.usedTime;
		this.currentGroup = x.currentGroup;
	}

	/**
	 * Method "addToPath"
	 * - add a restaurant to this path
	 * 
	 * @param x[Restaurant] : restaurant that this path will visit
	 */
	public void addToPath(Restaurant x) {
		p.add(x);
		this.usedTime += x.stayTime;
		this.resVisited[x.num] = true;
	}

	/**
	 * @deprecated
	 * Method "removeFromPath"
	 * - remove a restaurant from this path
	 * 
	 * @param x[Restaurant] : restaurant that this path will not visit
	 */
	public void removeFromPath(Restaurant x) {
		if (p.contains(x)) {
			p.remove(x);
			this.usedTime -= x.stayTime;
			this.resVisited[x.num] = false;
		}
	}

	/**
	 * @deprecated
	 * Method "getLastObject"
	 * 
	 * @return the last visited restaurant
	 */
	public Restaurant getLastObject() {
		if (!p.isEmpty()) {
			return p.get(p.size() - 1);
		} else {
			return null;
		}
	}

	/**
	 * Method "PrintObject"
	 * 
	 * @return the converted string value of this path
	 */
	public String printObject() {
		String result = "";
		for (Restaurant x : p) {
			result = result + x.name + " --> ";
		}
		result = result + "END/" + this.usedTime;
		return result;
	}

	/**
	 * Method "changeBetter"
	 * - change this path with bestWay(main) if this path is more useful
	 */
	public void changeBetter() {
		if (this.usedTime < Main.bestWay.usedTime) {
			Main.bestWay = new Path(this);
		}
	}

	/**
	 * Method "isPromising"
	 * 
	 * @return true if date was not finished
	 */
	public boolean isPromising() {
		return this.usedTime <= Main.endDate;
	}

	/**
	 * Method "isAvailable"
	 * 
	 * @param r[Restaurant] : restaurant that this path want to visit
	 * @return true if this restaurant is not in break
	 */
	public boolean isAvailable(Restaurant r) {
		if (this.usedTime + r.stayTime <= r.breakStart || this.usedTime >= r.breakEnd) {
			return true;
		} else {
			return false;
		}
	}
}

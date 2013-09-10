package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.Serializable;

import android.annotation.SuppressLint;

/**
 * This class is used to create an item object.
 * 
 * @author acranford6
 * @version 1.0 26Feb2013
 */
public class Item implements Comparable<Item>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String category;
	private String city;
	private String state;
	private String description;
	private String creator;
	private boolean isFound;
	private String reward;
	private boolean isResolved;
	private int month;
	private int day;
	private int year;
	private String user;
	private Integer sortPri;

	/**
	 * Constructor
	 * 
	 * This is the constructor for the item class
	 * 
	 * @param title
	 * @param category
	 * @param location
	 * @param description
	 * @param status
	 * @param reward
	 * @param type
	 * @param month
	 * @param day
	 * @param year
	 * @param user
	 */
	public Item(String title, String category, String city, String state,
			String description, boolean isFound, String reward, int month,
			int day, int year, boolean isResolved, String user) {
		this.title = title;
		this.category = category;
		this.city = city;
		this.state = state;
		this.description = description;
		this.isFound = isFound;
		this.reward = reward;
		this.isResolved = isResolved;
		this.month = month;
		this.day = day;
		this.year = year;
		this.user = user;
		this.sortPri = 0;

	}// end Constructor

	/**
	 * Gets the title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}// end getTitle

	/**
	 * Gets the category
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}// end getCategory

	/**
	 * Gets the city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}// end getLocation

	/**
	 * Gets the State
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Gets the description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}// end getDescription

	/**
	 * Get the Creator string
	 * 
	 * @return creator string
	 */
	public String getCreator() {
		return creator;
	}// end getCreator

	/**
	 * Get isFound boolean
	 * 
	 * @return isFound
	 */
	public boolean getIsFound() {
		return isFound;
	}// end getStatus

	/**
	 * Get reward
	 * 
	 * @return reward
	 */
	public String getReward() {
		return reward;
	}// end getReward

	/**
	 * Get isResolved
	 * 
	 * @return isResloved
	 */
	public boolean getIsResolved() {
		return isResolved;
	}// end getType

	/**
	 * Get the month of the item was created
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}// end getMonth

	/**
	 * Get the day of the item was created
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}// end getDay

	/**
	 * Get the year of the item was created
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}// end getYear

	/**
	 * Get the user string of the user that created the item
	 * 
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Get the sortPri of the item
	 * 
	 * @return sort Priority
	 */
	public Integer getSortPri() {
		return sortPri;
	}


	/**
	 * Set the title of the item
	 * 
	 * @param arg
	 */
	public void setTitle(String arg) {
		title = arg;
	}// end setTitle

	/**
	 * Set the category of the item
	 * 
	 * @param arg
	 */
	public void setCategory(String arg) {
		category = arg;
	}// end setCategory

	/**
	 * Set the city of the item
	 * 
	 * @param arg
	 */
	public void setCity(String arg) {
		city = arg;
	}// end setLocation

	/**
	 * Set the state of the item
	 * 
	 * @param arg
	 */
	public void setState(String arg) {
		state = arg;
	}// end setState

	/**
	 * Set the description of the item
	 * 
	 * @param arg
	 */
	public void setDescription(String arg) {
		description = arg;
	}// end setDescription

	/**
	 * Set the creator of the item
	 * 
	 * @param arg
	 */
	public void setCreator(String arg) {
		creator = arg;
	}// end setCreator

	/**
	 * Set the status of the item
	 * 
	 * @param arg
	 */
	public void setIsFound(boolean arg) {
		isFound = arg;
	}// end setStatus

	/**
	 * Set the reward of the item
	 * 
	 * @param arg
	 */
	public void setReward(String arg) {
		reward = arg;
	}// end setReward

	/**
	 * Set the title of the item
	 * 
	 * @param arg
	 */
	public void setisResolved(boolean arg) {
		isResolved = arg;
	}// end setType

	/**
	 * Set the title of the item
	 * 
	 * @param arg
	 */
	public void setMonth(int arg) {
		month = arg;
	}// end setMonth

	/**
	 * Set the title of the item
	 * 
	 * @param arg
	 */
	public void setDay(int arg) {
		day = arg;
	}// end setDay

	/**
	 * Set the title of the item
	 * 
	 * @param arg
	 */
	public void setYear(int arg) {
		year = arg;
	}// end setYear

	/**
	 * Set the user of the item
	 * 
	 * @param arg
	 */
	public void setUser(String arg) {
		user = arg;
	}

	/**
	 * Set the sortPri of the item if given an Integer
	 * 
	 * @param sortPri
	 */
	public void setSortPri(Integer sortPri) {
		this.sortPri = sortPri;
	}

	/**
	 * Set the sortPri of the item if given an int primitive
	 * 
	 * @param sortPri
	 */
	public void setSortPri(int sortPri) {
		Integer temp = sortPri;
		this.sortPri = temp;
	}

	/**
	 * Increment the value of sortPri
	 */
	@SuppressLint("UseValueOf")
	public void incSortPri() {
		this.sortPri = new Integer(this.sortPri.intValue() + 1);
	}

	/**
	 * Implement the natural order for this class
	 */
	public int compareTo(Item c) {
		if (getSortPri() < c.getSortPri())
			return 1;
		else if (getSortPri() > c.getSortPri())
			return -1;
		else
			return 0;
	}

}// end Item

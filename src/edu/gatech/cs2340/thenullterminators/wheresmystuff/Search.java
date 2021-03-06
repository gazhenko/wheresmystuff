package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import android.annotation.SuppressLint;
import java.util.ArrayList;

/**
 * This class searches the text files for specified items then returns the
 * results
 * 
 * @author theNullTerminators (Group 12)
 * @version 0.1
 * 
 */
@SuppressLint("DefaultLocale")
public class Search  {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public static ArrayList<Item> items = new ArrayList<Item>();

	/**
	 * adds an item to the arraylist
	 * 
	 * @param item
	 *            item to be added to list
	 */
	public static void addItem(Item item) {
		items.add(item);
	}

	/**
	 * search full database by title
	 * 
	 * @param title
	 *            title to search by
	 * @return filtered arraylist
	 */
	@SuppressLint("DefaultLocale")
	public static ArrayList<Item> byTitle(String title) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getTitle().toLowerCase()
					.contains(title.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * searches user list by title
	 * 
	 * @param title
	 *            users title
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	@SuppressLint("DefaultLocale")
	public static ArrayList<Item> byTitle(String title, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getTitle().toLowerCase()
					.contains(title.toLowerCase())) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list to find matches by title and increment priority
	 * variable if a match is found
	 * 
	 * @param title
	 *            to match
	 * @param uList
	 *            users lsit
	 */
	public static void forMatchByTitle(String title, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getTitle().toLowerCase()
					.contains(title.toLowerCase())) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database by category
	 * 
	 * @param category
	 *            users category
	 * @return filtered list
	 */
	public static ArrayList<Item> byCategory(String category) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getCategory().toLowerCase()
					.equals(category.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by category
	 * 
	 * @param category
	 *            to search
	 * @param uList
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byCategory(String category,
			ArrayList<Item> uList) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getCategory().toLowerCase()
					.equals(category.toLowerCase())) {
				newList.add(uList.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list to find matches by category and increment priority
	 * variable if a match is found
	 * 
	 * @param category
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByCategory(String category, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getCategory().toLowerCase()
					.equals(category.toLowerCase())) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database for city
	 * 
	 * @param city
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byCity(String city) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getCity().toLowerCase().equals(city.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for city
	 * 
	 * @param city
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byCity(String city, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getCity().toLowerCase().equals(city.toLowerCase())) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for match by city and increment priority variable if a
	 * match is found
	 * 
	 * @param city
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByCity(String city, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getCity().toLowerCase().equals(city.toLowerCase())) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database by state
	 * 
	 * @param state
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byState(String state) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getState().toLowerCase()
					.equals(state.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by state
	 * 
	 * @param state
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byState(String state, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getState().toLowerCase()
					.equals(state.toLowerCase())) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * serach users list for match by state and increment a priority variable if
	 * a match is found
	 * 
	 * @param state
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByState(String state, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getState().toLowerCase()
					.equals(state.toLowerCase())) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database by description
	 * 
	 * @param description
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byDescription(String description) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDescription().toLowerCase()
					.equals(description.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by description
	 * 
	 * @param description
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byDescription(String description,
			ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getDescription().toLowerCase()
					.equals(description.toLowerCase())) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database by isFound
	 * 
	 * @param isFound
	 *            boolean to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byIsFound(boolean isFound) {
		ArrayList<Item> newList = new ArrayList<Item>();

		for(int i = 0;i < items.size();i++)
		{
			if(items.get(i).getIsFound() == isFound)
			{
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by isFound boolean
	 * 
	 * @param isFound
	 *            boolean to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byIsFound(boolean isFound,
			ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getIsFound() == isFound) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database by reward
	 * 
	 * @param reward
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byReward(String reward) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getReward().toLowerCase()
					.equals(reward.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by reward
	 * 
	 * @param reward
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byReward(String reward, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getReward().toLowerCase()
					.equals(reward.toLowerCase())) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database by exact month
	 * 
	 * @param month
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byMonthExact(int month) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getMonth() == month) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by month
	 * 
	 * @param month
	 *            month to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byMonthExact(int month, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getMonth() == month) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for match by month and increment a priority variable if
	 * a match is found
	 * 
	 * @param month
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByMonth(int month, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getMonth() == month) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database for month after month given
	 * 
	 * @param month
	 *            to search after
	 * @return filtered search
	 */
	public static ArrayList<Item> byMonthAfter(int month) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getMonth() > month) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for month after month given
	 * 
	 * @param month
	 *            to search after
	 * @param ulist
	 *            users list
	 * @return filtered search
	 */
	public static ArrayList<Item> byMonthAfter(int month, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getMonth() > month) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database for month before month given
	 * 
	 * @param month
	 *            to search before
	 * @return filtered search
	 */
	public static ArrayList<Item> byMonthBefore(int month) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getMonth() < month) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for month before month given
	 * 
	 * @param month
	 *            to search before
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byMonthBefore(int month, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getMonth() < month) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database by day
	 * 
	 * @param day
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byDayExact(int day) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDay() == day) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by day
	 * 
	 * @param day
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byDayExact(int day, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getDay() == day) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for match by day and increment a priority variable if a
	 * match is found
	 * 
	 * @param day
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByDay(int day, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getDay() == day) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database for day after day given
	 * 
	 * @param day
	 *            to search after
	 * @return filtered search
	 */
	public static ArrayList<Item> byDayAfter(int day) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDay() > day) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for day after day given
	 * 
	 * @param day
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byDayAfter(int day, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getDay() > day) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database for day before day given
	 * 
	 * @param day
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byDayBefore(int day) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDay() < day) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for day before day given
	 * 
	 * @param day
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byDayBefore(int day, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getDay() < day) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database by year
	 * 
	 * @param year
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearExact(int year) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getYear() == year) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list by year
	 * 
	 * @param year
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearExact(int year, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getYear() == year) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for match by year and increment a priority variable if
	 * a match is found
	 * 
	 * @param year
	 *            to match
	 * @param uList
	 *            users list
	 */
	public static void forMatchByYear(int year, ArrayList<Item> uList) {
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getYear() == year) {
				uList.get(i).incSortPri();
			}
		}
	}

	/**
	 * search full database for year after year given
	 * 
	 * @param year
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearAfter(int year) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getYear() > year) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for year after year given
	 * 
	 * @param year
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearAfter(int year, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getYear() > year) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database for year before year given
	 * 
	 * @param year
	 *            to search
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearBefore(int year) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getYear() < year) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for year before year given
	 * 
	 * @param year
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byYearBefore(int year, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getYear() < year) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database for boolean isResolved
	 * 
	 * @param isResolved
	 *            boolean to search
	 * @return filtered search
	 */
	public static ArrayList<Item> byIsResolved(boolean isResolved) {
		ArrayList<Item> newList = new ArrayList<Item>();

		for(int i = 0;i < items.size();i++)
		{
			if(items.get(i).getIsResolved() == isResolved)
			{

				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for boolean isResloved
	 * 
	 * @param isResolved
	 *            boolean to search
	 * @param ulist
	 *            users list
	 * @return filtered list
	 */
	public static ArrayList<Item> byIsResolved(boolean isResolved,
			ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getIsResolved() == isResolved) {
				newList.add(ulist.get(i));
			}
		}
		return newList;
	}

	/**
	 * search full database for user
	 * 
	 * @param user
	 *            to search
	 * @return filtered search
	 */
	public static ArrayList<Item> byUser(String user) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getUser().toLowerCase().equals(user.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

	/**
	 * search users list for user
	 * 
	 * @param user
	 *            to search
	 * @param ulist
	 *            users list
	 * @return filtered search
	 */
	public static ArrayList<Item> byUser(String user, ArrayList<Item> ulist) {
		ArrayList<Item> newList = new ArrayList<Item>();
		for (int i = 0; i < ulist.size(); i++) {
			if (ulist.get(i).getUser().toLowerCase().equals(user.toLowerCase())) {
				newList.add(items.get(i));
			}
		}
		return newList;
	}

}

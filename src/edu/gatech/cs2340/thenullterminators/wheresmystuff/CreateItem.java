package edu.gatech.cs2340.thenullterminators.wheresmystuff;

/**
 * This class is used to create
 * a new item that will be added to
 * the search database of items and also the 
 * user's database and the application
 * database.
 * @author acranford6
 * @version 1.0 02MAR2013 acranford6
 *
 */
public class CreateItem {
	
	public static void createItem(String title, String category, String city,String state, String description, boolean isLost,
			String reward,int month,int day,int year, boolean isResolved, User user)
	{
		Search.items.add( new Item(title,category,city,state,description,isLost,reward,month,day,year,isResolved, user.getUserName()));
	}// end createItem
}// end CreateItem

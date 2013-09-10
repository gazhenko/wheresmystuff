package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

/**
 * Controls the functionality of the match item page
 * 
 * @author theNullTerminators
 * 
 */
public class MatchItemActivity extends Activity {

	private ListView matchList;
	private ArrayAdapter<String> matchAdapter;
	private Context context;
	private static int clickTask_item;
	private ArrayList<Item> matchArrayList = new ArrayList<Item>();
	private ArrayList<String> matchTitles = new ArrayList<String>();
	private CharSequence text;
	private int duration = Toast.LENGTH_SHORT, month, day, year;
	private String lostOrFound, title, city, state, category;
	private boolean isFound;

	/**
	 * filters a data structure to not include already resolved items, then only
	 * lost or found, based on if the page that is looking for a match. The
	 * title, city state, category, month, day, and year and then displays the
	 * results
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match);
		context = getApplicationContext();
		matchList = (ListView) findViewById(R.id.match_list);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		isFound = !bundle.getBoolean("isFound");
		title = bundle.getString("title");
		city = bundle.getString("city");
		state = bundle.getString("state");
		category = bundle.getString("category");
		month = bundle.getInt("month");
		day = bundle.getInt("day");
		year = bundle.getInt("year");
		matchArrayList = matchItem(title, city, state, category, month, day,
				year, isFound);

		if (matchArrayList.size() > 0) {

			for (int i = 0; i < matchArrayList.size(); i++) {
				if (matchArrayList.get(i).getIsFound() == true) {

					lostOrFound = "Found";

				} else if (matchArrayList.get(i).getIsFound() == false) {

					lostOrFound = "Lost";

				}
				matchTitles.add(matchArrayList.get(i).getTitle() + " : "
						+ lostOrFound);
			}// end for
		} else {
			matchTitles.add("No Items");
		}
		matchAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, matchTitles);
		matchList.setAdapter(matchAdapter);

		matchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			/**
			 * makes the items in the list clickable
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String itemName = ((TextView) view).getText().toString();
				// find the ":" in the title
				int index_of_endName = itemName.indexOf(":");
				if (!itemName.equals("No Items")) {

					itemName = itemName.substring(0, index_of_endName - 1);

				}
				for (int i = 0; i < Search.items.size(); i++) {

					if (Search.items.get(i).getTitle().equals(itemName)) {

						clickTask_item = i;

					}

				}
				// check to make sure the text you click is an item
				if (!itemName.equals("No Items")) {

					Intent myIntent = new Intent(view.getContext(),
							ItemActivity.class);
					myIntent.putExtra("item", clickTask_item);
					view.getContext().startActivity(myIntent);

				}

			}

		});

	}

	/**
	 * sets up the menu for the page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_match, menu);
		return true;
	}

	/**
	 * creates the intent for each option in menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logOut:
			Login.logOut();
			Intent intent = new Intent(this, MainActivity.class);
			this.startActivity(intent);
			break;
		case R.id.user_profile:
			Intent intent3 = new Intent(this, UserActivity.class);
			this.startActivity(intent3);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	/**
	 * controls back button functionality
	 */
	public void onBackPressed() {

		Intent intent = new Intent(this, UserActivity.class);
		this.startActivity(intent);

	}

	/**
	 * Sorts items based on match priority
	 * 
	 * @param title
	 * @param city
	 * @param state
	 * @param category
	 * @param month
	 * @param day
	 * @param year
	 * @param isFound
	 * @return sorted list
	 */
	public ArrayList<Item> matchItem(String title, String city, String state,
			String category, int month, int day, int year, boolean isFound) {

		matchArrayList = Search.byIsResolved(false);
		matchArrayList = Search.byIsFound(!isFound, matchArrayList);
		Search.forMatchByTitle(title, matchArrayList);
		Search.forMatchByCity(city, matchArrayList);
		Search.forMatchByState(state, matchArrayList);
		Search.forMatchByCategory(category, matchArrayList);
		Search.forMatchByMonth(month, matchArrayList);
		Search.forMatchByDay(day, matchArrayList);
		Search.forMatchByYear(year, matchArrayList);
		Collections.sort(matchArrayList, new ItemComparator());
		return matchArrayList;
	}

	/**
	 * Sorts items based on match priority
	 * 
	 * @param title
	 * @param city
	 * @param state
	 * @param category
	 * @param month
	 * @param day
	 * @param year
	 * @param isFound
	 * @param matchArrayList
	 * @return sorted list
	 */
	public static ArrayList<Item> matchItem(String title, String city,
			String state, String category, int month, int day, int year,
			boolean isFound, ArrayList<Item> matchArrayList) {

		matchArrayList = Search.byIsResolved(false, matchArrayList);
		matchArrayList = Search.byIsFound(!isFound, matchArrayList);
		Search.forMatchByTitle(title, matchArrayList);
		Search.forMatchByCity(city, matchArrayList);
		Search.forMatchByState(state, matchArrayList);
		Search.forMatchByCategory(category, matchArrayList);
		Search.forMatchByMonth(month, matchArrayList);
		Search.forMatchByDay(day, matchArrayList);
		Search.forMatchByYear(year, matchArrayList);
		Collections.sort(matchArrayList, new ItemComparator());
		return matchArrayList;
	}

}

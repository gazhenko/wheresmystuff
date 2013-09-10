package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Controls the functionality of the filter page
 * 
 * @author theNullTerminators
 * 
 */
public class FilterItems extends Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// local variables
	private EditText nameInput, rewardInput, locStateInput, locCityInput,
			monthInput, dayInput, yearInput;
	private Spinner categoryList;
	private RadioGroup rgDate, rgLostFound, rgResolve;
	private RadioButton after, before, exact, resolve, notResolve;
	private Button bFilter;
	private Button bCancel;
	private String strName, strReward, strLocCity, strLocState, strMonth,
			strDay, strYear, strCategory;
	private boolean boolFound, boolLost, isResolved, isNotResolved = false;
	private int rewardChoice, dateChoice;
	private ArrayList<Item> filteredArray = Search.items;

	/**
	 * Creates the fields that can be filtered by
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_items);

		// set up local variables with their respective id's
		after = (RadioButton) findViewById(R.id.search_date_button_above);
		before = (RadioButton) findViewById(R.id.search_date_button_before);
		exact = (RadioButton) findViewById(R.id.search_date_button_exact);
		resolve = (RadioButton) findViewById(R.id.search_button_resolve);
		notResolve = (RadioButton) findViewById(R.id.search_button_notReslove);
		nameInput = (EditText) findViewById(R.id.search_name_input);
		rewardInput = (EditText) findViewById(R.id.search_reward_input);
		locStateInput = (EditText) findViewById(R.id.search_state_input);
		locCityInput = (EditText) findViewById(R.id.search_city_input);
		monthInput = (EditText) findViewById(R.id.search_month_input);
		dayInput = (EditText) findViewById(R.id.search_day_input);
		yearInput = (EditText) findViewById(R.id.search_year_input);
		categoryList = (Spinner) findViewById(R.id.search_category_input);
		rgDate = (RadioGroup) findViewById(R.id.search_dateRadioGroup);
		rgLostFound = (RadioGroup) findViewById(R.id.search_lostFound_radioGroup);
		rgResolve = (RadioGroup) findViewById(R.id.search_resolve_radioGroup);
		bFilter = (Button) findViewById(R.id.search_button_filter);
		bCancel = (Button) findViewById(R.id.search_button_cancel);

		// what happens when you click the filter button
		bFilter.setOnClickListener(new View.OnClickListener() {
			/**
			 * Checks the filter values and then filters a list and returns the
			 * filtered list to the search page
			 */
			@Override
			public void onClick(View v) {
				// set the strings to inputs from the layout
				strName = nameInput.getText().toString();
				strReward = rewardInput.getText().toString();
				strLocState = locStateInput.getText().toString();
				strLocCity = locCityInput.getText().toString();
				strMonth = monthInput.getText().toString();
				strDay = dayInput.getText().toString();
				strYear = yearInput.getText().toString();
				strCategory = categoryList.getSelectedItem().toString();

				int radioInt = rgLostFound.getCheckedRadioButtonId();
				if (radioInt == R.id.search_button_item_found) {
					boolFound = true;
					boolLost = false;
				} else if (radioInt == R.id.search_button_item_lost) {
					boolFound = false;
					boolLost = true;
				}

				int radioInt2 = rgLostFound.getCheckedRadioButtonId();
				if (radioInt2 == R.id.search_button_resolve) {
					isResolved = true;
					isNotResolved = false;
				} else if (radioInt2 == R.id.search_button_notReslove) {
					isResolved = false;
					isNotResolved = true;
				}

				// check through the filter options to see if there needs to be
				// a search
				filteredArray = searchItems(strName, strReward, strLocCity,
						strLocState, strMonth, strDay, strYear, strCategory,
						boolFound, boolLost, after.isChecked(),
						before.isChecked(), exact.isChecked(),
						resolve.isChecked(), notResolve.isChecked());

				// return filtered array back to previous intent
				Intent myGoto1 = new Intent(v.getContext(),
						SearchActivity.class);
				myGoto1.putExtra("array", filteredArray);
				v.getContext().startActivity(myGoto1);

			}

		});

		// what happens when you click the cancel button
		bCancel.setOnClickListener(new View.OnClickListener() {
			/**
			 * returns the original list back to the search page
			 */
			@Override
			public void onClick(View v) {

				// return filtered array back to previous intent
				Intent myGoto1 = new Intent(v.getContext(),
						SearchActivity.class);
				myGoto1.putExtra("array", filteredArray);
				v.getContext().startActivity(myGoto1);

			}

		});

	}

	/**
	 * Filters a list by the values given
	 * 
	 * @param name
	 * @param reward
	 * @param locCity
	 * @param locState
	 * @param month
	 * @param day
	 * @param year
	 * @param category
	 * @param boolFound
	 * @param boolLost
	 * @param isAfter
	 * @param isBefore
	 * @param isExact
	 * @param isResolved
	 * @param isNotResolved
	 * @return filtered list
	 */
	private static ArrayList<Item> searchItems(String name, String reward,
			String locCity, String locState, String month, String day,
			String year, String category, boolean boolFound, boolean boolLost,
			boolean isAfter, boolean isBefore, boolean isExact,
			boolean isResolved, boolean isNotResolved) {

		ArrayList<Item> array = Search.items;

		if (!name.equals("")) {
			array = Search.byTitle(name, array);
		}
		if (!reward.equals("")) {
			array = Search.byReward(reward, array);
		}
		if (!locCity.equals("")) {
			array = Search.byCity(locCity, array);
		}
		if (!locState.equals("")) {
			array = Search.byState(locState, array);
		}
		if (!month.equals("") && isAfter) {
			array = Search.byMonthAfter(Integer.parseInt(month), array);
		}
		if (!month.equals("") && isBefore) {
			array = Search.byMonthBefore(Integer.parseInt(month), array);
		}
		if (!month.equals("") && isExact) {
			array = Search.byMonthExact(Integer.parseInt(month), array);
		}
		if (!day.equals("") && isAfter) {
			array = Search.byDayAfter(Integer.parseInt(day), array);
		}
		if (!day.equals("") && isBefore) {
			array = Search.byDayBefore(Integer.parseInt(day), array);
		}
		if (!day.equals("") && isExact) {
			array = Search.byDayExact(Integer.parseInt(day), array);
		}
		if (!year.equals("") && isAfter) {
			array = Search.byYearAfter(Integer.parseInt(year), array);
		}
		if (!year.equals("") && isBefore) {
			array = Search.byYearBefore(Integer.parseInt(year), array);
		}
		if (!year.equals("") && isExact) {
			array = Search.byYearExact(Integer.parseInt(year), array);
		}
		if (isResolved || isNotResolved) {
			array = Search.byIsResolved(isResolved, array);
		}
		if (!category.equals("General") && !category.equals(null)
				&& !category.equals("")) {
			array = Search.byCategory(category, array);
		}
		if (boolFound || boolLost) {
			array = Search.byIsFound(boolFound, array);
		}

		return array;
	}

	/**
	 * Set up menu for page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_search, menu);
		return true;
	}

	/**
	 * Creates intent for menu button pressed
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

}

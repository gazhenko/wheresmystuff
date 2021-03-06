package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Class is used to show display all of the elements of an item to the
 * users. This page also offers a feature that finds the best match for the item
 * the user is currently looking at.
 * 
 * @author theNullTerminators
 * @version 1.0
 * 
 */
public class ItemActivity extends Activity {

	private TextView dateOutput, nameOutput, rewardOutput, cityOutput, stateOutput,
					  statusOutput, descriptionOutput, userOutput;
	private Item item;
	private CharSequence text;
	private int duration = Toast.LENGTH_SHORT;
	private Context context;

	/**
	 * Create and fill text fields for output and and display to user
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		dateOutput = (TextView) findViewById(R.id.item_activity_date);
		nameOutput = (TextView) findViewById(R.id.item_activity_name);
		rewardOutput = (TextView) findViewById(R.id.item_activity_reward);
		cityOutput = (TextView) findViewById(R.id.item_activity_city);
		stateOutput = (TextView) findViewById(R.id.item_activity_state);
		statusOutput = (TextView) findViewById(R.id.item_activity_status);
		descriptionOutput = (TextView) findViewById(R.id.item_activity_description_input);
		userOutput = (TextView) findViewById(R.id.usrLabel3);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		item = Search.items.get(bundle.getInt("item"));
		String temp = "Date Added: " + item.getMonth() + "/" + item.getDay()
				+ "/" + item.getYear();
		dateOutput.setText(temp);
		nameOutput.setText(item.getTitle());
		rewardOutput.setText(item.getReward());
		cityOutput.setText(item.getCity());
		stateOutput.setText(item.getState());
		userOutput.setText(item.getUser());
		String temp2 = "Lost";
		if (item.getIsFound()) {
			temp2 = "Found";
		}
		statusOutput.setText(temp2);
		descriptionOutput.setText(item.getDescription());

	}

	/**
	 * Set up menu for page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_item, menu);
		return true;
	}

	/**
	 * If match is pressed, item must be not resolved. Creates intent for menu
	 * item pressed.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem menu) {
		context = getApplicationContext();
		switch (menu.getItemId()) {
		case R.id.match_item:
			if (item.getIsResolved()) {
				text = "Cannot Match a Resolved Item";
				Toast.makeText(context, text, duration).show();
			} else {
				Intent intent = new Intent(this, MatchItemActivity.class);
				intent.putExtra("title", item.getTitle());
				intent.putExtra("city", item.getCity());
				intent.putExtra("state", item.getState());
				intent.putExtra("category", item.getCategory());
				intent.putExtra("month", item.getMonth());
				intent.putExtra("day", item.getDay());
				intent.putExtra("year", item.getYear());
				intent.putExtra("isFound", item.getIsFound());
				intent.putExtra("isResolved", item.getIsResolved());
				this.startActivity(intent);
			}
			break;
		case R.id.logOut:
			Login.logOut();
			Intent intent2 = new Intent(this, MainActivity.class);
			this.startActivity(intent2);
			break;
		case R.id.user_profile:
			Intent intent4 = new Intent(this, UserActivity.class);
			this.startActivity(intent4);
			break;
		default:
			return super.onOptionsItemSelected(menu);
		}
		return true;
	}

}

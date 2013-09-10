package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

import edu.gatech.cs2340.thenullterminators.database.SQLiteOpenHelperWMS;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Controls the functionality of the create item page
 * 
 * @author theNullTerminators
 * 
 */
public class CreateItemActivity extends Activity {

	EditText etTitle, etReward, etCity, etState, etDescription;
	RadioButton rbLost, rbFound;
	RadioGroup rGroup;
	Button bCreate, bCancel;
	String titleStr, rewardStr, cityStr, stateStr, descriptionStr, categoryStr, LostOrFound;
	Spinner spin;
	boolean rbChecked, rbFinal; // Found if true, Lost if false;

	Context context;
	CharSequence text;
	int duration = Toast.LENGTH_SHORT;

	/**
	 * creates the text fields for the item
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_item);
		etTitle = (EditText) findViewById(R.id.item_title_input);
		spin = (Spinner) findViewById(R.id.item_category_input);
		etReward = (EditText) findViewById(R.id.item_reward_input);
		etCity = (EditText) findViewById(R.id.item_city_input);
		etState = (EditText) findViewById(R.id.item_state_input);
		etDescription = (EditText) findViewById(R.id.item_description_input);
		rbLost = (RadioButton) findViewById(R.id.button_item_lost);
		rbFound = (RadioButton) findViewById(R.id.button_item_found);
		bCreate = (Button) findViewById(R.id.button_create_item);
		bCancel = (Button) findViewById(R.id.button_cancel_create);
		rGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		bCreate.setOnClickListener(new View.OnClickListener() {

			/**
			 * Does error checking on the user input and then adds the new item
			 * to the database
			 */
			@Override
			public void onClick(View v) {

				titleStr = etTitle.getText().toString();
				categoryStr = spin.getSelectedItem().toString();
				rewardStr = etReward.getText().toString();
				cityStr = etCity.getText().toString();
				stateStr = etState.getText().toString();
				descriptionStr = etDescription.getText().toString();
				context = getApplicationContext();
				int radioInt = rGroup.getCheckedRadioButtonId();
				if (radioInt == R.id.button_item_found) {
					rbFinal = true;
				} else if (radioInt == R.id.button_item_lost) {
					rbFinal = false;
				}

				// check radioButton status
				if (!rbLost.isChecked() && !rbFound.isChecked()) {
					// return message that all entries must be filled!
					text = "All entries must be filled!";
				} else if (titleStr.length() < 1 || cityStr.length() < 1
						|| stateStr.length() < 1) {

					// return message all entries must be filled!
					text = "All entries must be filled!";
				} else {

					// grab all the variables and add them to a new Item
					Date date = new Date(); // your date
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
//					CreateItem.createItem(titleStr, categoryStr, cityStr,
//							stateStr, descriptionStr, rbFinal, rewardStr,
//							month + 1, day, year, false, Login.currentUser);

					if(rbFinal == false){
						LostOrFound = "false";
					}
					else{
						LostOrFound = "true";
					}
					// send to output stream
					//try {
						// FileOutputStream fileOut = new
						// FileOutputStream("User.ser");
						// ObjectOutputStream out = new
						// ObjectOutputStream(fileOut);
						// out.writeObject(Search.items);
						// out.close();
						// fileOut.close();
						
//						FileOutputStream fos = context.openFileOutput(
//								"Item.ser", Context.MODE_PRIVATE);
//						ObjectOutputStream os = new ObjectOutputStream(fos);
//						os.writeObject(Search.items);
//						os.close();
//						fos.close();
//						boolean ai = false;
//						System.out.println("I sent to file!");
//						System.out.println("File Sent length: "
//								+ Search.items.size());
						SQLiteOpenHelperWMS entry = new SQLiteOpenHelperWMS(CreateItemActivity.this);
						entry.open();
						entry.createEntryItem(titleStr, categoryStr, cityStr, stateStr, descriptionStr, LostOrFound, 
												rewardStr, month, day, year, "false", Login.currentUser);
						System.out.println("storing items to DB");
						entry.close();
//					} catch (Exception i) {
//
//						i.printStackTrace();
//						System.out.println("I didn't send to file!");
//
//					}

					// Display popup: Created New Item
					text = ("Item Created Succesfully!!");
					MediaPlayer ourSong = MediaPlayer.create(
							CreateItemActivity.this, R.raw.creatednewitemsound);
					ourSong.start();
					Intent myGoto = new Intent(v.getContext(),
							UserActivity.class);
					v.getContext().startActivity(myGoto);
				}// end if

				Toast.makeText(context, text, duration).show();

			} // end of onClick
		}); // end of bCreate.setOnClickListener

		bCancel.setOnClickListener(new View.OnClickListener() {

			/**
			 * Backs out of create item page without any change
			 * 
			 */
			@Override
			public void onClick(View v) {
				context = getApplicationContext();
				text = "Create item Canceled";
				Intent myGoto = new Intent(v.getContext(), UserActivity.class);
				v.getContext().startActivity(myGoto);
				Toast.makeText(context, text, duration).show();
			} // end of onClick
		}); // end of bCancel.setOnClickListener

	} // end of onCreate

	/**
	 * Check the state of the radio button
	 * 
	 * @param view
	 *            radio button view
	 */
	public void onRadioButtonClicked(View view) {
		context = getApplicationContext();
		// Is this button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {

		case R.id.button_item_found:
			if (checked) {
				// set rbChecked to true
				rbChecked = true;
			}

		case R.id.button_item_lost:
			if (checked) {
				// set rbChecked to false
				rbChecked = false;
			}// end if

		}// end switch
	}// end onRadioButtonClicked

	/**
	 * Set up pages menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_item, menu);
		return true;
	}// end onCrateOptionsMenu

	/**
	 * creates intent for menu option pressed
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.user_profile:
			Intent intent1 = new Intent(this, UserActivity.class);
			this.startActivity(intent1);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

}// end CreateItemActivity
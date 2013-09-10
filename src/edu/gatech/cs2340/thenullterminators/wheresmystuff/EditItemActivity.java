package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import edu.gatech.cs2340.thenullterminators.database.SQLiteOpenHelperWMS;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Controls the functionality of the edit item page
 * 
 * @author theNullTerminators
 * 
 */
public class EditItemActivity extends Activity {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	// local variables
	private EditText etTitle, etReward, etCity, etState, etDescription;
	private Spinner spinner;
	private RadioGroup radioGroupFound, radioGroupResolved;
	private RadioButton rbFound;
	private RadioButton rbLost;
	private RadioButton rbResolved;
	private RadioButton rbNotResolved;
	private Button bEdit, bCancel;
	private boolean boolFound, boolResolved = false;
	private String text;
	private String cat;
	SQLiteOpenHelperWMS  info = new SQLiteOpenHelperWMS(this);
	/**
	 * Creates the fields used to edit the item and adds the current values to
	 * the field
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		
//		String name = Login.users.get(Activity.clickTask).getUserName();
//		System.out.println(name);
//		info.open();		
//		info.getUserData();
//		//info.getItemData();
//		final long rowid = info.getUserRowid(name);
//	    System.out.println(" i am printing my rowid now "+rowid);
//		System.out.println("I am  going to grab the users from DB");
//		info.close();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		// Set IDs
		etTitle = (EditText) findViewById(R.id.item_title_input_edit);
		etReward = (EditText) findViewById(R.id.item_reward_input_edit);
		etCity = (EditText) findViewById(R.id.item_city_input_edit);
		etState = (EditText) findViewById(R.id.item_state_input_edit);
		etDescription = (EditText) findViewById(R.id.item_description_input_edit);
		spinner = (Spinner) findViewById(R.id.item_category_input_edit);
		radioGroupFound = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroupResolved = (RadioGroup) findViewById(R.id.radioGroup2);
		rbFound = (RadioButton) findViewById(R.id.button_item_found_edit);
		rbLost = (RadioButton) findViewById(R.id.button_item_lost_edit);
		rbResolved = (RadioButton) findViewById(R.id.button_item_resolved_edit);
		rbNotResolved = (RadioButton) findViewById(R.id.button_item_unresolved_edit);
		bEdit = (Button) findViewById(R.id.button_edit_item_edit);
		bCancel = (Button) findViewById(R.id.button_cancel_create_edit);

		// set test to item's current info
		Intent intent = getIntent();
		if (intent.hasExtra("item")) {
			Bundle bundle = intent.getExtras();
			etTitle.setText(Search.items.get(bundle.getInt("item")).getTitle());
			etReward.setText(Search.items.get(bundle.getInt("item"))
					.getReward());
			etCity.setText(Search.items.get(bundle.getInt("item")).getCity());
			etState.setText(Search.items.get(bundle.getInt("item")).getState());
			etDescription.setText(Search.items.get(bundle.getInt("item"))
					.getDescription());
			
			String title = Search.items.get(bundle.getInt("item")).getTitle();
			info.open();		
			info.getItemData();
			final long rowid = info.getItemRowid(title);
		    System.out.println(" i am printing my rowid now "+rowid);
			System.out.println("I am  going to grab the items from DB");
			info.close();
			
			if (Search.items.get(bundle.getInt("item")).getIsFound()) {
				rbFound.setChecked(true);
			} else {
				rbLost.setChecked(true);
			}

			if (Search.items.get(bundle.getInt("item")).getIsResolved()) {
				rbResolved.setChecked(true);
			} else {
				rbNotResolved.setChecked(true);
			}
			cat = Search.items.get(bundle.getInt("item")).getCategory();
			if (cat.equals("General")) {
				spinner.setSelection(0);
			} else if (cat.equals("Furniture")) {
				spinner.setSelection(1);
			} else if (cat.equals("Jewelry")) {
				spinner.setSelection(2);
			} else if (cat.equals("Clothing")) {
				spinner.setSelection(3);
			} else if (cat.equals("Electronics")) {
				spinner.setSelection(4);
			} else {
				spinner.setSelection(5);
			}

		}

		bEdit.setOnClickListener(new View.OnClickListener() {

			/**
			 * takes the input and updates the values for the item
			 */
			@Override
			public void onClick(View v) {
				// Set all the inputs to strings
				String titleStr = etTitle.getText().toString();
				String rewardStr = etReward.getText().toString();
				String cityStr = etCity.getText().toString();
				String stateStr = etState.getText().toString();
				String descriptionStr = etDescription.getText().toString();
				Context context = getApplicationContext();
				int radioIntFound = radioGroupFound.getCheckedRadioButtonId();
				if (radioIntFound == R.id.button_item_found_edit)
					boolFound = true;
				else if (radioIntFound == R.id.button_item_lost_edit)
					boolFound = false;
				int radioIntResol = radioGroupResolved
						.getCheckedRadioButtonId();
				if (radioIntResol == R.id.button_item_resolved_edit)
					boolResolved = true;
				else if (radioIntResol == R.id.button_item_unresolved_edit)
					boolResolved = false;
				// check radioButton status
				if (!rbLost.isChecked() && !rbFound.isChecked()) {
					// return message that all entries must be filled!
					text = "All entries must be filled!";
				} else if (titleStr == null || rewardStr == null
						|| cityStr == null || stateStr == null
						|| descriptionStr == null) {

					// return message all entries must be filled!
					text = "All entries must be filled!";
				} else {

					// Save all the inputs to the current item
					Intent intent = getIntent();
					if (intent.hasExtra("item")) {
						Bundle bundle = intent.getExtras();
						Search.items.get(bundle.getInt("item")).setTitle(
								titleStr);
						Search.items.get(bundle.getInt("item")).setReward(
								rewardStr);
						Search.items.get(bundle.getInt("item"))
								.setCity(cityStr);
						Search.items.get(bundle.getInt("item")).setState(
								stateStr);
						Search.items.get(bundle.getInt("item")).setDescription(
								descriptionStr);
						Search.items.get(bundle.getInt("item")).setIsFound(
								boolFound);
						Search.items.get(bundle.getInt("item")).setisResolved(
								boolResolved);
						Search.items.get(bundle.getInt("item")).setCategory(
								spinner.getSelectedItem().toString());
					}

					// send to output stream
					try {

						FileOutputStream fos = context.openFileOutput(
								"Item.ser", Context.MODE_PRIVATE);
						ObjectOutputStream os = new ObjectOutputStream(fos);
						os.writeObject(Search.items);
						os.close();
						fos.close();

					} catch (IOException i) {

						i.printStackTrace();

					}

					// Display popup: Created New Item
					text = "Successfully Edited Item!";
					Intent myGoto = new Intent(v.getContext(),
							UserActivity.class);
					v.getContext().startActivity(myGoto);
				}// end if

				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

			}
		});

		bCancel.setOnClickListener(new View.OnClickListener() {

			/**
			 * Exits the create item page with
			 */
			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				text = "Item Edit Canceled";
				Intent myGoto = new Intent(v.getContext(), UserActivity.class);
				v.getContext().startActivity(myGoto);
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			} // end of onClick
		}); // end of bCancel.setOnClickListener

	}

	/**
	 * Sets up menu for page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

}

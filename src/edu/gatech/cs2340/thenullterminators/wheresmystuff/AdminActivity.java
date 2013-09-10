package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.util.ArrayList;

import edu.gatech.cs2340.thenullterminators.database.SQLiteOpenHelperWMS;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Controls the functionality in the 
 * admin page.
 * 
 * @author Qiang Guo
 * @version 1.0 11March2013 Qiang Guo
 */
public class AdminActivity extends Activity {
	private ListView lockList, userList, adminList;
	private ArrayAdapter<String> lockAdapt, userAdapter, adminAdapt;
	private Context context, context2;
	private Button showLock, showUser, showAdmin;
	public static int clickTask;
	private ArrayList<String> userNames = new ArrayList<String>();
	private ArrayList<String> tempList = new ArrayList<String>();
	SQLiteOpenHelperWMS entry = new SQLiteOpenHelperWMS(this);
	/**
	 * Sets up the buttons for filtering
	 * and also sets up the lists to display
	 * the users.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		entry.open();
		entry.getUserData();
		entry.close();
		
		context = getApplicationContext();
		context2 = this; 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		final TextView userTextView = (TextView) findViewById(R.id.admin_text1);
		userTextView.setText(Login.getCurrentUser().getUserName());
		showLock = (Button) findViewById(R.id.admin_showlocked);
		showUser = (Button) findViewById(R.id.admin_showreg);
		showAdmin = (Button) findViewById(R.id.admin_showadmin);
		userList = (ListView) findViewById(R.id.admin_userList);
		for (int i = 0; i < Login.users.size(); i++) {
			userNames.add(Login.users.get(i).getUserName());
		}
		if (userNames.isEmpty()) {
			userNames.add("No Users");
		}
		userAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, userNames);
		userList.setAdapter(userAdapter);

		showLock.setOnClickListener(new View.OnClickListener() {
			/**
			 * shows all current locked users
			 */
			@Override
			public void onClick(View v) {
				tempList.clear();
				for (int i = 0; i < Login.users.size(); i++) {
					if (Login.users.get(i).getIsLocked()) {
						tempList.add(Login.users.get(i).getUserName());
					}
				}
				if (tempList.isEmpty()) {
					tempList.add("No Users Locked out");
				}
				userNames = tempList;
				userAdapter = new ArrayAdapter<String>(context2,
						android.R.layout.simple_list_item_1, userNames);
				userList.setAdapter(userAdapter);
			}
		}); // end register listener

		showUser.setOnClickListener(new View.OnClickListener() {
			/**
			 * shows all current users
			 */
			@Override
			public void onClick(View v) {
				tempList.clear();
				for (int i = 0; i < Login.users.size(); i++) {
					if (!Login.users.get(i).getIsAdmin()) {
						tempList.add(Login.users.get(i).getUserName());
					}
				}
				if (tempList.isEmpty()) {
					tempList.add("No Users");
				}
				userNames = tempList;
				userAdapter = new ArrayAdapter<String>(context2,
						android.R.layout.simple_list_item_1, userNames);
				userList.setAdapter(userAdapter);
			}
		}); // end register listener

		showAdmin.setOnClickListener(new View.OnClickListener() {
			/**
			 * shows all current admin users
			 */
			@Override
			public void onClick(View v) {
				tempList.clear();
				for (int i = 0; i < Login.users.size(); i++) {
					if (Login.users.get(i).getIsAdmin()) {
						tempList.add(Login.users.get(i).getUserName());
					}
				}
				if (tempList.isEmpty()) {
					tempList.add("No Admin Users");
				}
				userNames = tempList;
				userAdapter = new ArrayAdapter<String>(context2,
						android.R.layout.simple_list_item_1, userNames);
				userList.setAdapter(userAdapter);
			}
		}); // end register listener

		userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			/**
			 * responds to the user clicking on the list view item
			 */
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				String name = ((TextView) view).getText().toString();
				for (int i = 0; i < Login.users.size(); i++) {
					if (Login.users.get(i).getUserName().equals(name))
						clickTask = i;
				}
				Intent myIntent = new Intent(view.getContext(),
						AdminEditActivity.class);
				view.getContext().startActivity(myIntent);
			}
		});

	}

	/**
	 * sets up this pages menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admin_menu, menu);
		return true;
	}

	/**
	 * creates a new intent based on the users click. Goes to
	 * the create admin user page or log out.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.createAdminUser:
			Intent intent = new Intent(this, CreateAdminActivity.class);
			this.startActivity(intent);
			break;
		case R.id.logOut:
			Login.logOut();
			Intent intent2 = new Intent(this, MainActivity.class);
			this.startActivity(intent2);
			break;
		default:
			return super.onOptionsItemSelected(item);

		}
		return true;
	}

	/**
	 * back button is disabled
	 */
	@Override
	public void onBackPressed() {

		// do nothing!

	}

}
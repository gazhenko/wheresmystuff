package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import edu.gatech.cs2340.thenullterminators.database.SQLiteOpenHelperWMS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Controls the functionality of the registration page
 * 
 * @author theNullTerminators
 * @version 1.0
 * 
 */
public class RegisterActivity extends Activity {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 2L;
	private EditText tUser, tPass, tPhon;
	private Button reg;
	private String tempName, tempPass, tempPhon;
	private Context context;
	private CharSequence text = "Hello toast!";
	private int duration = Toast.LENGTH_LONG;

	/**
	 * creates text field for username, password and phonenumber.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Intent intent = getIntent();
		tUser = (EditText) findViewById(R.id.reg_usr);
		tPass = (EditText) findViewById(R.id.reg_pass);
		tPhon = (EditText) findViewById(R.id.reg_phon);
		reg = (Button) findViewById(R.id.reg_toreg);
		context = getApplicationContext();
		if (intent.hasExtra("name")) {
			Bundle bundle = intent.getExtras();
			tUser.setText(bundle.getString("name"));
			tPass.setText(bundle.getString("pass"));
		}

		reg.setOnClickListener(new View.OnClickListener() {

			/**
			 * Does error check on data in text fields and if correct registers
			 * the new user
			 */
			@Override
			public void onClick(View v) {
				tempName = tUser.getText().toString();
				tempPass = tPass.getText().toString();
				tempPhon = tPhon.getText().toString();
				int checkVal = Login.registerUser(tempName, tempPass, tempPhon);
				if (checkVal == -1) {
					text = "Username already exists";
					Toast.makeText(context, text, duration).show();
				} else if (checkVal == -2) {
					text = "Username should be a valid email address";
					Toast.makeText(context, text, duration).show();
				} else if (checkVal == -3) {
					text = "Username cannot be blank";
				} else if (checkVal == -4) {
					text = "Password must be at least 5 characters";
				} else if (checkVal == -5) {
					text = "Password cannot be blank";
				} else {
					// write to output stream
					
					SQLiteOpenHelperWMS entry = new SQLiteOpenHelperWMS(RegisterActivity.this);
					entry.open();
					entry.createEntryUser(tempName, tempPass, tempPhon,"false","false");
					entry.close();
					
//					try {
//
////						FileOutputStream fos = context.openFileOutput(
////								"User.ser", Context.MODE_PRIVATE);
////						ObjectOutputStream os = new ObjectOutputStream(fos);
////						os.writeObject(Login.users);
////						os.close();
////						fos.close();
////						System.out.println("I sent to file!");
//
//					} catch (IOException i) {
//
//						i.printStackTrace();
//						System.out.println("I didn't send the file!");
//
//					}

					text = "Register successful!!";
					Toast.makeText(context, text, duration).show();
					Login.loginUser(tempName, tempPass);
					if (!Login.currentUser.getIsAdmin()) {
						text = "Login successful!!";
						Intent myGoto = new Intent(v.getContext(),
								UserActivity.class);
						v.getContext().startActivity(myGoto);
					} else if (Login.currentUser.getIsAdmin()) {
						text = "Login successful!!";
						Intent myGoto = new Intent(v.getContext(),
								AdminActivity.class);
						v.getContext().startActivity(myGoto);
					}
				}

				Toast.makeText(context, text, duration).show();
			}
		});

	}

	/**
	 * Sets up menu for this page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
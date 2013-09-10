package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Controls the functionality for the login page
 * 
 * @author theNullTerminators
 * 
 */
public class LoginActivity extends Activity {

	private EditText tUser, tPass;
	private Button log, reg;
	private String tempName, tempPass;

	private Context context;
	private CharSequence text;
	private int duration = Toast.LENGTH_SHORT;

	/**
	 * creates text field for username and password, and creates button for
	 * login and register
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tUser = (EditText) findViewById(R.id.log_usr);
		tPass = (EditText) findViewById(R.id.log_pass);
		log = (Button) findViewById(R.id.log_tolog);
		reg = (Button) findViewById(R.id.log_toreg);
		context = getApplicationContext();

		log.setOnClickListener(new View.OnClickListener() {

			/**
			 * does error checking on user input and if correct logs user in
			 */
			@Override
			public void onClick(View v) {
				tempName = tUser.getText().toString();
				tempPass = tPass.getText().toString();
				int checkVal = Login.loginUser(tempName, tempPass);
				if (checkVal == -1)
					text = "Username not found, please Register username";
				else if (checkVal == -3) {
					text = "This account has been locked out, please contact admin!";
				} else if (checkVal == -2) {
					checkVal = Login.addPassFail(tempName);
					if (checkVal == -1) {
						text = "Your account has been locked, please contact admin!";
					} else
						text = "Invalid password, please try again";
				} else if (checkVal == -4) {
					text = "You are already logged in!!";
				} else if (checkVal == -5) {
					text = "You must log out to log into a different account";
				} else {
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
				} // end if

				Toast.makeText(context, text, duration).show();
			}// end onClick
		}); // end login listener

		/**
		 * collects text box entries and sends them to register page
		 */
		reg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myGoto = new Intent(v.getContext(),
						RegisterActivity.class);
				myGoto.putExtra("name", tempName);
				myGoto.putExtra("pass", tempPass);
				v.getContext().startActivity(myGoto);
			}
		}); // end register listener
	}// end onCreate

	/**
	 * Sets up menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
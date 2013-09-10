package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import edu.gatech.cs2340.thenullterminators.database.SQLiteOpenHelperWMS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Controls the functionality for the
 * create admin page
 * 
 * @author theNullTerminators
 *
 */
public class CreateAdminActivity extends Activity {
	
	//private static final long serialVersionUID = 1L;
	EditText tUser, tPass;
	Button create;
	String tempName, tempPass;
	
	Context context;
	CharSequence text = "Hello toast!";
	int duration = Toast.LENGTH_SHORT;
	SQLiteOpenHelperWMS  info = new SQLiteOpenHelperWMS(this);
	
	/**
	 * creates textfield for username and password
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_admin);
		tUser = (EditText) findViewById(R.id.cradmin_usr);
		tPass = (EditText) findViewById(R.id.cradmin_pass);
		create = (Button) findViewById(R.id.cradmin_toadmin);
		context = getApplicationContext();
		
		create.setOnClickListener(new View.OnClickListener() {

			/**
			 * does error checking on users input data
			 * and creates new admin user
			 */
			@Override
			public void onClick(View v) {
				tempName = tUser.getText().toString();
				tempPass = tPass.getText().toString();
				int checkVal = Login.createAdmin(tempName, tempPass);
				if (checkVal == -1) 
				{
					text = "Username already exists";
					Toast.makeText(context, text, duration).show();
				} 
				else if(checkVal == -3)
				{
					text = "Username cannot be blank";
				}
				else if(checkVal == -4)
				{
					text = "Password must be at least 5 characters";
				}
				else if(checkVal == -5)
				{
					text = "Password cannot be blank";
				}
				else {
					text = "Register successful!!";
					// save to output stream
					//try {
//						
						info.open();
						info.createEntryUser(tempName, tempPass,"","false", "true");
						info.close();
//						FileOutputStream fos = context.openFileOutput("User.ser", Context.MODE_PRIVATE);
//						ObjectOutputStream os = new ObjectOutputStream(fos);
//						os.writeObject(Login.users);
//						os.close();
//						fos.close();
//						System.out.println("I sent to file!");
//						
//					} catch (IOException i) {
//						
//						i.printStackTrace();
//						System.out.println("I didn't send the file!");
//						
//					}
					Toast.makeText(context, text, duration).show();
					Intent myGoto = new Intent(v.getContext(), AdminActivity.class);
					v.getContext().startActivity(myGoto);
				}
				
				Toast.makeText(context, text, duration).show();
			}
		});
		
	}
	/**
	 * adds menu to the page
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


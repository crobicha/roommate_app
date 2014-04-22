package com.roboshed.roommateapp.ui;

import com.roboshed.roommateapp.R;
import com.roboshed.roommateapp.data.UserData;
import com.roboshed.roommateapp.util.Session;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserDataActivity extends Activity
{
	private UserData userData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_data);
		
		Session session = new Session(getApplicationContext());
		userData = session.getUserData();
		
		TextView userName = (TextView) findViewById(R.id.userName);
		userName.setText(userData.getUserName());
		userName.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(UserDataActivity.this);
				builder.setTitle("Title");

				// Set up the input
				final EditText input = new EditText(getApplicationContext());
				input.setBackgroundColor(Color.LTGRAY);
				input.setTextColor(Color.BLACK);
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				builder.setView(input);

				// Set up the buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        userData.setUserName(input.getText().toString());
				    }
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        dialog.cancel();
				    }
				});

				builder.show();
			}
		});
	}
}

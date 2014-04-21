package com.roboshed.roommateapp.ui;

import com.roboshed.roommateapp.R;
import com.roboshed.roommateapp.R.id;
import com.roboshed.roommateapp.R.layout;
import com.roboshed.roommateapp.R.menu;
import com.roboshed.roommateapp.network.NetworkClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Button showBills = (Button) findViewById(R.id.showBills);
		showBills.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), BillActivity.class);
				startActivity(intent);
			}
		});
		
		Button getToken = (Button) findViewById(R.id.getToken);
		getToken.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GetTokenTask task = new GetTokenTask(getApplicationContext());
				task.execute();
			}
		});
		
		Button userSettings = (Button) findViewById(R.id.userSettings);
		userSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				String email = ((EditText) findViewById(R.id.userEmail)).getText().toString();
//				String displayName = ((EditText) findViewById(R.id.userDisplayName)).getText().toString();
//				
//				CreateUserTask createUserTask = new CreateUserTask(getApplicationContext(), email, displayName);
//				createUserTask.execute();
				
				Intent intent = new Intent(getApplicationContext(), UserDataActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private class GetTokenTask extends AsyncTask<String, Integer, String>
	{
		private Context context;
		
		public GetTokenTask(Context context)
		{
			this.context = context.getApplicationContext();
			
		}
		@Override
		protected String doInBackground(String... params) 
		{
			NetworkClient client = NetworkClient.getInstance(getApplicationContext());
			
//			client.auth("crobicha@gmail.com");
			
			client.getUserData();
			
			return null;
		}
	}
	
	private class CreateUserTask extends AsyncTask<String, Integer, String>
	{
		private Context context;
		private String email;
		private String displayName;
		
		public CreateUserTask(Context context, String email, String displayName)
		{
			this.context = context.getApplicationContext();
			this.email = email;
			this.displayName = displayName;
		}
		@Override
		protected String doInBackground(String... params) 
		{
			NetworkClient client = NetworkClient.getInstance(getApplicationContext());
			
			client.createUser(email, displayName);
			
			return null;
		}
	}
}

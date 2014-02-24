package com.roboshed.roommateapp.network;

import org.apache.http.client.methods.HttpPost;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.roboshed.roommateapp.data.UserData;

public class NetworkClient 
{
	private static final String TAG = "NetworkClient";
	
	private final String appClientId = "124478093443-a7rlmgh1d4s534dada52q4b3aietngjb.apps.googleusercontent.com";
	private final String scope = "audience:server:client_id:124478093443-cq88n839hi59ldkvm3ko3cqlbglarv64.apps.googleusercontent.com";
	
	// TODO: Change this to https
	private final String serverUrl = "http://passenger.roommates.roboshed.com/";
	
	private static NetworkClient instance = null;
	
	private Context context;
	
	private String token;
	
	private NetworkClient(Context context)
	{
		this.context = context.getApplicationContext();
	}
	
	public static NetworkClient getInstance(Context context)
	{
		if(instance == null)
			instance = new NetworkClient(context);
		
		return instance;
	}
	
	public String auth(String accountName)
	{
		try
		{
			token = GoogleAuthUtil.getToken(context, accountName, scope);
			
			Log.d(TAG, "Google auth token = " + token);
		}
		catch (Exception e) 
		{
			Log.e(TAG, "Error getting google auth token", e);
		}
		
		return token;
	}
	
	public UserData getUserData()
	{
		HttpPost post = new HttpPost(uri)
	}
}

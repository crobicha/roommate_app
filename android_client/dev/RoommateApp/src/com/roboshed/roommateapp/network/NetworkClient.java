package com.roboshed.roommateapp.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
	private final String serverUrl = "http://api.roommates.roboshed.com/";
	
	/*
	 * api.roommates.roboshed.com
	 */
	
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
			
			// Check if it is valid
			
		}
		catch (Exception e) 
		{
			Log.e(TAG, "Error getting google auth token", e);
		}
		
		return token;
	}
	
//	public UserData getUserData()
	public void getUserData()
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(serverUrl + "userdata");
		
		try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("googleToken", token));
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(post);
	        
		    Log.i(TAG, "Response status = " + response.getStatusLine());
		    Log.i(TAG, "Response = " + response);
	    } 
		catch (Exception e) {
	        Log.d(TAG, "Error getting user data", e);
	        return;
	    }
	    

	}
}

package com.roboshed.roommateapp.network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.textservice.TextInfo;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.roboshed.roommateapp.data.UserData;

public class NetworkClient 
{
	private static final String TAG = "NetworkClient";
	
	private final String appClientId = "124478093443-a7rlmgh1d4s534dada52q4b3aietngjb.apps.googleusercontent.com";
	private final String scope = "audience:server:client_id:124478093443-cq88n839hi59ldkvm3ko3cqlbglarv64.apps.googleusercontent.com";
	
	// TODO: Change this to https
	private final String serverUrl = "http://dev-roommate.roboshed.com/";
	
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
	
	public void createUser(String email, String displayName)
	{
		if(TextUtils.isEmpty(displayName)) {
			displayName = email;
		}
		
		Log.d(TAG, "Creating user " + email + ", " + displayName);
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(serverUrl + "users");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("user[email]", email));
		params.add(new BasicNameValuePair("user[displayName]", displayName));
		
//		String paramString = URLEncodedUtils.format(params, "utf-8");
//		HttpGet get = new HttpGet(serverUrl + "users/new?" + paramString);
		
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			
			// Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(post);
	        
		    Log.i(TAG, "Response status = " + response.getStatusLine());
		    Log.i(TAG, "Response = " + response);
		} 
		catch (Exception e) {
			Log.e(TAG, "Error creating user " + email, e);
		}
		
	}
}

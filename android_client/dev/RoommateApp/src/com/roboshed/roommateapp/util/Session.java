package com.roboshed.roommateapp.util;

import java.util.LinkedList;
import java.util.List;

import com.roboshed.roommateapp.data.UserData;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class Session 
{
	private static Session instance;
	public static Session getInstance(Context context)
	{
		if(instance == null)
			instance = new Session(context);
		
		return instance;
	}
	
	public Session(Context context)
	{
		userData = new UserData(context);
	}
	
	private UserData userData;
	
	public UserData getUserData()
	{
		return userData;
	}
	
	public void setUserData(UserData userData)
	{
		this.userData = userData;
	}
	
	// source: https://stackoverflow.com/questions/2727029/how-can-i-get-the-google-username-on-android
	public static String getUsername(Context context)
	{
	    AccountManager manager = AccountManager.get(context); 
	    Account[] accounts = manager.getAccountsByType("com.google"); 
	    List<String> possibleEmails = new LinkedList<String>();

	    for (Account account : accounts) {
	      // TODO: Check possibleEmail against an email regex or treat
	      // account.name as an email address only for certain account.type values.
	      possibleEmails.add(account.name);
	    }

	    if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
	        String email = possibleEmails.get(0);
	        String[] parts = email.split("@");
	        if(parts.length > 0 && parts[0] != null)
	            return parts[0];
	        else
	            return null;
	    }else
	        return null;
	}
}

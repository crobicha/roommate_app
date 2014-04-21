package com.roboshed.roommateapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData 
{
	private int userId;
	private String userName;
	
	private Context context;
	private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;
	
	private static final String sharedPrefsFilename = "UserData.xml";
	
	public UserData(Context context)
	{
		this.context = context;
		sharedPref = context.getSharedPreferences(sharedPrefsFilename, Context.MODE_PRIVATE);
	    prefEditor = sharedPref.edit();
	}

	public int getUserId()
	{
        return sharedPref.getInt("userId" , -1);
	}
	
	public void setUserId(int userId)
	{
        prefEditor.putInt("userId" , userId);
        prefEditor.commit();
	}
	
	public String getUserName()
	{
		return sharedPref.getString("userId" , "error");
	}
	
	public void setUserName(String userName)
	{
		prefEditor.putString("userName" , userName);
        prefEditor.commit();
	}
}
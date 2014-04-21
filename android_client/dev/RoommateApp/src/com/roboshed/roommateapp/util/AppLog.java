package com.roboshed.roommateapp.util;

import android.util.Log;

public class AppLog {
	private static final String AppName = "RoommateApp";
	
	public static void d(String tag, String message)
	{
		Log.d(AppName, tag + ":" + message);
	}
	
	public static void w(String tag, String message)
	{
		Log.w(AppName, tag + ":" + message);
	}
	
	public static void e(String tag, String message)
	{
		Log.e(AppName, tag + ":" + message);
	}
}

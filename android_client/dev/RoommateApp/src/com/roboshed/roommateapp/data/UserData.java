package com.roboshed.roommateapp.data;

public class UserData 
{
	private int userId;
	private String userName;
	
	public UserData(int userId, String userName)
	{
		this.userId = userId;
		this.userName = userName;
	}

	public int getUserId()
	{
		return userId;
	}
	
	public String getUserName()
	{
		return userName;
	}
}

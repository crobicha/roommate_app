package com.roboshed.roommateapp.data;

public class Bill {
	public int userId;
	public double amount;
	public String description;
	public BillType billType;
	public long date;
	
	public Bill(int userId, BillType billType, double amount)
	{
		init(userId, billType, amount, "", System.currentTimeMillis()/1000);
	}
	public Bill(int userId, BillType billType, double amount, String description)
	{
		init(userId, billType, amount, description, System.currentTimeMillis()/1000);
	}
	
	public Bill(int userId, BillType billType, double amount, String description, long date)
	{
		init(userId, billType, amount, description, date);
	}
	
	private void init(int userId, BillType billType, double amount, String description, long date)
	{
		this.userId = userId;
		this.billType = billType;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}
	
	public void markPaid()
	{
		// Call API
	}
	
	public enum BillType {
		GAS,
		ELECTRIC,
		INTERNET_AND_TV,
		HOUSE_SUPPLIES, // Paper towels, Toilet paper
		HOUSE_FOOD,
		RENT,
		WATER,
		OTHER
	}
}

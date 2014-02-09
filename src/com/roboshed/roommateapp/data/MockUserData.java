package com.roboshed.roommateapp.data;

import java.util.ArrayList;
import java.util.List;

import com.roboshed.roommateapp.data.Bill.BillType;

public class MockUserData 
{
	private MockUserData()
	{
		
	}
	
	private static MockUserData instance;
	
	public static MockUserData getInstance()
	{
		if(instance == null)
				instance = new MockUserData();
		
		return instance;
	}
	
	public int getUserId()
	{
		return 1;
	}
	
	public String getUserNameFromId(int userId)
	{
		if(userId == 1)
			return "Chris";
		else if(userId == 2)
			return "Cristiam";
		else if(userId == 3)
			return "Harold";
		
		return "Unknown user";
	}
	
	public List<Integer> getGroupMembers()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1); // Chris
		list.add(2); // Cristiam
		list.add(3); // Harold
		
		return list;
	}
	
	public List<Bill> getBillList()
	{
		List<Bill> billList = new ArrayList<Bill>();
	
		billList.add(new Bill(1, BillType.INTERNET_AND_TV, 139.15));
		billList.add(new Bill(2, BillType.ELECTRIC, 200));
		billList.add(new Bill(2, BillType.WATER, 342.26));
		billList.add(new Bill(3, BillType.GAS, 342.26));
		
		return billList;
	}

}

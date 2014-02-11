package com.roboshed.roommateapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateFromEpoch(long date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(new Date(date));
	}
}

package com.akhil.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date covertStringToDate(String date) throws ParseException {
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);  
		return date1;
		
	}
	
}

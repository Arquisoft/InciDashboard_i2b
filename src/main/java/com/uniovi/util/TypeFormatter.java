package com.uniovi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeFormatter {
	//The formatter is always the same so makes sense to use it as a field
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String timestampToDateString(Long timestamp) {
		long time = timestamp*1000;
		Date date = new Date(time);
		String string  = dateFormat.format(date);
		return string;
	}
	
}

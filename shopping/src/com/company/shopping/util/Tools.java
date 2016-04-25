package com.company.shopping.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
}

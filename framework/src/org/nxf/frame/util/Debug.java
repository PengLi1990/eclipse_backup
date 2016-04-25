package org.nxf.frame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
	public static void info(String info){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		str = "["+sdf.format(new Date())+"]"+info;
		System.out.println(str);
	}
	public static void info(Object obj,String info){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		str = "["+sdf.format(new Date())+"]["+obj.getClass().getName()+"]"+info;
		System.out.println(str);
	}
};

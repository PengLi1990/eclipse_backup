package com.buaa.hive;

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Phone2Area extends UDF{
	private static HashMap<String,String> areaMap = new HashMap<String,String>();
	
	static{
		areaMap.put("1388", "beijing");
		areaMap.put("1399", "tianjin");
		areaMap.put("1366", "nanjing");
	}
	
	public String evaluate(String phone){
		String result;
		result = areaMap.get(phone.substring(0, 4)) == null ? phone + "	waixing":phone + areaMap.get(phone.substring(0, 4));
		return result;
	}
}

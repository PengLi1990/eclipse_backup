package com.buaa.threaddemo;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
//		TestThread tt = new TestThread();
//		TestThread tt1 = new TestThread();
//		tt.start();
//		tt1.start();
//		for(int j=0;j<80;j++){
//			System.out.println("Ö÷Ïß³Ì" + j);
//		}
		
		String date = String.format("yyyy-mm-dd",new Date());
		System.out.println(date);
	}

}

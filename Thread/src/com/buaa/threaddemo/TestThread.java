package com.buaa.threaddemo;

public class TestThread extends Thread{

	@Override
	public void run() {
		
		for(int i=0; i<80; i++){
			System.out.println(this.getName() + i);
		}
	}
	
}



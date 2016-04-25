package com.buaa.ticket;

public class Ticket extends Thread {
	private static int number = 100;
	@Override
	public void run() {
		while(true){
			if(number > 0){
				System.out.println(Thread.currentThread().getName() + "ÂôÆ±" + number-- );
			}
		}
	}

}

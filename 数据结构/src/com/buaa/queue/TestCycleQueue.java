package com.buaa.queue;

public class TestCycleQueue {

	public static void main(String[] args) {
		MyCycleQueue mq = new MyCycleQueue();
		mq.insert(1);
		mq.insert(2);
		mq.insert(3);
		mq.insert(4);
		mq.insert(5);
		System.out.println("队满了？" + mq.isFull());
		System.out.println("队空了？" + mq.isEmpty());
		
		while(mq.getDataSize() != 0){
			System.out.println(mq.quit());
		}
		
		//再插入就会出现数组越界问题
		mq.insert(5);
	}

}

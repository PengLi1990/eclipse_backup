package com.buaa.queue;

public class TestQueue {

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		mq.insert(1);
		mq.insert(2);
		mq.insert(3);
		mq.insert(4);
		mq.insert(5);
		System.out.println("�����ˣ�" + mq.isFull());
		System.out.println("�ӿ��ˣ�" + mq.isEmpty());
		
		while(mq.getDataSize() != 0){
			System.out.println(mq.quit());
		}
		
		//�ٲ���ͻ��������Խ������
		mq.insert(5);
	}

}

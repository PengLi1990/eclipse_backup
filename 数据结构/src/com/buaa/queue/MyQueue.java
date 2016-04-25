package com.buaa.queue;

public class MyQueue {
	//底层
	private int[] arr;
	//队头
	private int top;
	//队尾
	private int tail;
	//有效数字大小
	private int dataSize;
	
	public MyQueue(){
		arr = new int[5];
		top = -1;
		tail = -1;
		dataSize = 0;
	}
	
	public MyQueue(int size){
		arr = new int[size];
		top = -1;
		tail = -1;
		dataSize = 0;
	}
	
	//入队
	public void insert(int value){
		arr[++tail] = value;
		dataSize++;
	}
	
	//出队
	public int quit(){
		dataSize--;
		return arr[++top];
	}
	
	//查看
	public int peek(){
		return arr[top];
	}
	
	//队空
	public boolean isEmpty(){
		return tail == -1;
	}
	
	//队满
	public boolean isFull(){
		return tail == arr.length - 1;
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}
	
	
}

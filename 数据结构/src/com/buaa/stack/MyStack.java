package com.buaa.stack;

public class MyStack {
	private int[] arr;
	private int top; //栈顶指针
	
	public MyStack(){
		arr = new int[10];
		top = -1;
	}
	
	public MyStack(int size){
		arr = new int[size];
		top = -1;
	}
	
	//入栈
	public void insert(int value){
		arr[++top] = value;
	}
	
	//弹栈
	public int pop(){
		return arr[top--];
		
	}
	
	//查看
	public void peek(){
		System.out.println(arr[top]);
	}
	
	//是否非空
	public boolean isEmpty(){
		
		return top == -1;
	}
	
	//是否满栈
	public boolean isFull(){
		return top == arr.length-1;
	}
	
}

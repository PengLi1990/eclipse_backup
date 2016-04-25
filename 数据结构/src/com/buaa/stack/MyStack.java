package com.buaa.stack;

public class MyStack {
	private int[] arr;
	private int top; //ջ��ָ��
	
	public MyStack(){
		arr = new int[10];
		top = -1;
	}
	
	public MyStack(int size){
		arr = new int[size];
		top = -1;
	}
	
	//��ջ
	public void insert(int value){
		arr[++top] = value;
	}
	
	//��ջ
	public int pop(){
		return arr[top--];
		
	}
	
	//�鿴
	public void peek(){
		System.out.println(arr[top]);
	}
	
	//�Ƿ�ǿ�
	public boolean isEmpty(){
		
		return top == -1;
	}
	
	//�Ƿ���ջ
	public boolean isFull(){
		return top == arr.length-1;
	}
	
}

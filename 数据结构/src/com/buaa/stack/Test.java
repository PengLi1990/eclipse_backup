package com.buaa.stack;

public class Test {

	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		myStack.insert(10);
		myStack.insert(20);
		myStack.insert(30);
		myStack.insert(40);
		myStack.insert(50);
		
		System.out.println("ջ��Ԫ�أ�");
		myStack.peek();
		
		System.out.println("ջ�Ƿ�Ϊ�գ�" + myStack.isEmpty());
		
		System.out.println("ջ�Ƿ�Ϊ���ˣ�" + myStack.isFull());
		
		System.out.println("��һ��");
		myStack.peek();
		
		while(!myStack.isEmpty()){
			System.out.println(myStack.pop());
		}

	}

}

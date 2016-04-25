package com.buaa.stack;

public class Test {

	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		myStack.insert(10);
		myStack.insert(20);
		myStack.insert(30);
		myStack.insert(40);
		myStack.insert(50);
		
		System.out.println("栈顶元素：");
		myStack.peek();
		
		System.out.println("栈是否为空：" + myStack.isEmpty());
		
		System.out.println("栈是否为满了：" + myStack.isFull());
		
		System.out.println("弹一个");
		myStack.peek();
		
		while(!myStack.isEmpty()){
			System.out.println(myStack.pop());
		}

	}

}

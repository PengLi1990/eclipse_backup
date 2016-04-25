package com.buaa.link;

/*
 * 节点域
 */
public class Node {
	//数据域
	public int data;
	//指针域
	public Node next;
	
	public Node(){
		data = 0;
		next = null;
	}
	
	public Node(int value){
		data = value;
	}
	
	public void display(){
		System.out.println(data + " ");
	}

}

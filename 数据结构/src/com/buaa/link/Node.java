package com.buaa.link;

/*
 * �ڵ���
 */
public class Node {
	//������
	public int data;
	//ָ����
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

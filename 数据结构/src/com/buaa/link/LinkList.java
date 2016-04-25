package com.buaa.link;

public class LinkList {
	//头节点,充当临时变量
	private Node first;
	//实例化链表时调用
	public LinkList(){
		first = null;
	}
	
	//添加节点
	public void insert(int value){
		//创建新节点，并往节点的数据域中插入数据
		Node node = new Node(20);
		//将新节点的指针指向头结点first
		node.next = first;
		//移动临时变量
		first = node;
	}
	
	//删除节点
	public void remove(int value){
		
	}
	//查找节点
	//打印节点
	public void display(Node node){
		while(node.next != null){
			System.out.println(node.data);
		}
	}

}

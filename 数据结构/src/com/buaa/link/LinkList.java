package com.buaa.link;

public class LinkList {
	//ͷ�ڵ�,�䵱��ʱ����
	private Node first;
	//ʵ��������ʱ����
	public LinkList(){
		first = null;
	}
	
	//��ӽڵ�
	public void insert(int value){
		//�����½ڵ㣬�����ڵ���������в�������
		Node node = new Node(20);
		//���½ڵ��ָ��ָ��ͷ���first
		node.next = first;
		//�ƶ���ʱ����
		first = node;
	}
	
	//ɾ���ڵ�
	public void remove(int value){
		
	}
	//���ҽڵ�
	//��ӡ�ڵ�
	public void display(Node node){
		while(node.next != null){
			System.out.println(node.data);
		}
	}

}

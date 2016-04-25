package com.buaa.queue;
/**
 * ѭ������
 * @author ����
 * @time 2016��4��6������9:16:19
 */
public class MyCycleQueue {
	//�ײ�
	private int[] arr;
	//��ͷ
	private int top;
	//��β
	private int tail;
	//��Ч���ִ�С
	private int dataSize;
	
	public MyCycleQueue(){
		arr = new int[5];
		top = -1;
		tail = -1;
		dataSize = 0;
	}
	
	public MyCycleQueue(int size){
		arr = new int[size];
		top = -1;
		tail = -1;
		dataSize = 0;
	}
	
	//���
	public void insert(int value){
		if(tail == arr.length - 1){
			tail = -1;
		}
		arr[++tail] = value;
		dataSize++;
	}
	
	//����
	public int quit(){
		if(top == arr.length){
			top = -1;
		}
		dataSize--;
		return arr[++top];
	}
	
	//�鿴
	public int peek(){
		return arr[top];
	}
	
	//�ӿ�
	public boolean isEmpty(){
		return tail == -1;
	}
	
	//����
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

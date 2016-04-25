package com.buaa.singleton;
/**
 * �������ģʽȷ��һ����ֻ��һ��ʵ�����Լ������ṩʵ��
 * @author ����
 * @time 2016��4��13������8:46:47
 */
public class Singleton {
	//��������ṩ��ʵ������
	public static volatile Singleton uniqueInstance;
	
	//˽�й�������ȷ�����಻��ʵ�����������
	private Singleton(){}
	
	//�ṩ������ʵ����ķ���
	public static Singleton getInstance(){
		
		if(uniqueInstance == null){
			//����ͬ������ֹ���̷߳��ʲ���������
			synchronized(Singleton.class){
				//���ж�һ��
				if(uniqueInstance == null){
					uniqueInstance = new Singleton();
					return uniqueInstance;
				}
			}
		}
		return uniqueInstance;
	}
	
	/*
	 * ��������
	 */

}

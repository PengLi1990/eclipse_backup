package com.buaa.sort;
/**
 * ð������
 * @author ����
 * @time 2016��4��13������9:36:42
 */
public class BubbleSort {
	
	/*
	 * ð�������㷨
	 * 	�����Ƚϣ���һ����
	 */
	public static void bubleSort(int[] arr){
		System.out.println("����ǰ��");
		display(arr);
		System.out.println();
		
		for(int x=0;x<arr.length-1;x++){
			//�������ѭ��һ�������������еĲ���Ƚϵ�������Сֵ���Ѿ��������ˣ�����y�ıȽϴ���Ҫ����
			for(int y=0;y<arr.length-1-x;y++){
				if(arr[y] < arr[y+1]){
					int temp = arr[y];
					arr[y] = arr[y+1];
					arr[y+1] = temp;
				}			
			}
			System.out.println("��" + x + "�֣�");
			display(arr);
			System.out.println();
		}
		
		System.out.println();
		System.out.println("�����");
		display(arr);
	}
	
	/*
	 * ��ӡ����
	 */
	public static void display(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]);
		}
	}
	

}

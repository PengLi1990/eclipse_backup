package com.buaa.sort;
/**
 * ѡ������
 * @author ����
 * @time 2016��4��13������9:36:23
 */
public class SelectSort {
	/*
	 * ѡ�������㷨
	 */
	public void selectSort(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i] > arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					
				}
			}
			
		}
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

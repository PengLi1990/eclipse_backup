package com.buaa.sort;
/**
 * 选择排序
 * @author 李鹏
 * @time 2016年4月13日下午9:36:23
 */
public class SelectSort {
	/*
	 * 选择排序算法
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
	 * 打印数组
	 */
	public static void display(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]);
		}
	}

}

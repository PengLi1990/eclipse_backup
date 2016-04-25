package com.buaa.sort;
/**
 * 冒泡排序
 * @author 李鹏
 * @time 2016年4月13日下午9:36:42
 */
public class BubbleSort {
	
	/*
	 * 冒泡排序算法
	 * 	两两比较，逐一沉底
	 */
	public static void bubleSort(int[] arr){
		System.out.println("排序前：");
		display(arr);
		System.out.println();
		
		for(int x=0;x<arr.length-1;x++){
			//下面这个循环一旦跳出，数组中的参与比较的最大或最小值就已经沉到底了，所以y的比较次数要减少
			for(int y=0;y<arr.length-1-x;y++){
				if(arr[y] < arr[y+1]){
					int temp = arr[y];
					arr[y] = arr[y+1];
					arr[y+1] = temp;
				}			
			}
			System.out.println("第" + x + "轮：");
			display(arr);
			System.out.println();
		}
		
		System.out.println();
		System.out.println("排序后：");
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

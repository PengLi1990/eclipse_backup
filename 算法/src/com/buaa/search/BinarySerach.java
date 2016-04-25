package com.buaa.search;

import org.junit.Test;

/**
 * 二分查找
 * @author 李鹏
 * @time 2016年4月13日下午9:55:38
 */
public class BinarySerach {
	
	public int binarySearch(int key,int[] arr){
		int min = 0;
		int max = arr.length-1;
		int mid = 0;
		
		while(min < max){
			mid = (max + min) / 2;
			if(key < arr[mid]){
				max = mid -1;
			}else if(key > arr[mid]){
				min = mid + 1;
			}else{
				return mid;
			}
			//没有找到的情况
			if (max<min){
				return -1;
			}

			//重新计算中间索引值
			mid = (min+max)/2;
		}
		return mid;
	}
	
	@Test
	public void search(){
		int[] arr = new int[]{2,3,4,6,8,9};
		System.out.println("查找内容的索引值为：");
		System.out.println(binarySearch(9,arr));
	}
}

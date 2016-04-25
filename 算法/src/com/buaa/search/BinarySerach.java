package com.buaa.search;

import org.junit.Test;

/**
 * ���ֲ���
 * @author ����
 * @time 2016��4��13������9:55:38
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
			//û���ҵ������
			if (max<min){
				return -1;
			}

			//���¼����м�����ֵ
			mid = (min+max)/2;
		}
		return mid;
	}
	
	@Test
	public void search(){
		int[] arr = new int[]{2,3,4,6,8,9};
		System.out.println("�������ݵ�����ֵΪ��");
		System.out.println(binarySearch(9,arr));
	}
}

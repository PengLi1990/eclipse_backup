package com.buaa.sort;

public class TestSort {

	public static void main(String[] args) {
		int[] arr = new int[]{1,4,2,6,3,5,7};
		//new BubbleSort().bubleSort(arr);
		new SelectSort().selectSort(arr);
	}

}

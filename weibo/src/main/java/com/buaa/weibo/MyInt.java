package com.buaa.weibo;
/**
 * 为Integer类型构造一个对象
 * @author 李鹏
 * @time 2016年5月7日下午8:54:36
 */
public class MyInt implements Comparable<MyInt> {
	
	private Integer value;
	
	

	public MyInt(Integer value) {
		
		this.value = value;
	}



	public Integer getValue() {
		return value;
	}



	public void setValue(Integer value) {
		this.value = value;
	}



	public int compareTo(MyInt o) {
		
		return value.compareTo(o.getValue());
	}

}

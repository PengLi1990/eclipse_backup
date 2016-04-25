package com.buaa.singleton;
/**
 * 单例设计模式确保一个类只有一个实例，自己对外提供实例
 * @author 李鹏
 * @time 2016年4月13日上午8:46:47
 */
public class Singleton {
	//定义对外提供的实例变量
	public static volatile Singleton uniqueInstance;
	
	//私有构造器，确保外类不能实例化本类对象
	private Singleton(){}
	
	//提供对外访问单例的方法
	public static Singleton getInstance(){
		
		if(uniqueInstance == null){
			//加入同步锁防止多线程访问产生的问题
			synchronized(Singleton.class){
				//再判断一次
				if(uniqueInstance == null){
					uniqueInstance = new Singleton();
					return uniqueInstance;
				}
			}
		}
		return uniqueInstance;
	}
	
	/*
	 * 其他代码
	 */

}

package com.buaa.hadoop1.weibo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 自定义微博类接口
 * @author 李鹏
 * @time 2016年4月27日下午10:38:51
 */
public class WeiboWritable implements WritableComparable<Object> {
	private int fans;//粉丝
	private int followers;//关注度
	private int status;//微博数

	/*
	 * 写出
	 * 实现WritableComparable的write()方法，以便该数据能被序列化后完成网络传输或文件输出 
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(fans);
		out.writeInt(followers);
		out.writeInt(status);
	}

	/*
	 * 读入
	 * 实现WritableComparable的readFields()方法，以便该数据能被序列化后完成网络传输或文件输入
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		this.fans = in.readInt();
		this.followers = in.readInt();
		this.status = in.readInt();
	}


	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	public int getFans() {
		return fans;
	}

	public int getFollowers() {
		return followers;
	}

	public int getStatus() {
		return status;
	}
	
	public void set(int fans, int followers, int status){
		this.fans = fans;
		this.followers = followers;
		this.status = status;
	}

	public WeiboWritable(int fans, int followers, int status) {
		this.fans = fans;
		this.followers = followers;
		this.status = status;
	}
	
	public WeiboWritable(){}

}

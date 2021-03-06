package com.buaa.hadoop1.score;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
/**
 * 自定义writable类
 * 为了便于每个学生学习成绩的计算，这里我们需要自定义一个 ScoreWritable 
 * 类实现 WritableComparable 接口，将学生各门成绩封装起来。
 * @author 李鹏
 * @time 2016年4月25日下午2:36:37
 */
public class ScoreWritable implements WritableComparable<Object> {
	private float chinese;
	private float math;
	private float english;
	private float physics;
	private float chemistry;
	

	public ScoreWritable() {
		
	}

	public ScoreWritable(float chinese, float math, float english, float physics, float chemistry) {
	
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.physics = physics;
		this.chemistry = chemistry;
	}
	
	public void set(float chinese, float math, float english, float physics, float chemistry){
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.physics = physics;
		this.chemistry = chemistry;
	}

	public float getChinese() {
		return chinese;
	}

	public float getMath() {
		return math;
	}

	public float getEnglish() {
		return english;
	}

	public float getPhysics() {
		return physics;
	}

	public float getChemistry() {
		return chemistry;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeFloat(chinese);
		out.writeFloat(math);
		out.writeFloat(physics);
		out.writeFloat(english);
		out.writeFloat(chemistry);
		
	}

	/*
	 * 
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		chinese = in.readFloat();
		math = in.readFloat();
		english = in.readFloat();
		physics = in.readFloat();
		chemistry = in.readFloat();
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}
	
}

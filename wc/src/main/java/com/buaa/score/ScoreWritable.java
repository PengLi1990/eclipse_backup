package com.buaa.score;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
/**
 * �Զ���writable��
 * Ϊ�˱���ÿ��ѧ��ѧϰ�ɼ��ļ��㣬����������Ҫ�Զ���һ�� ScoreWritable 
 * ��ʵ�� WritableComparable �ӿڣ���ѧ����ųɼ���װ������
 * @author ����
 * @time 2016��4��25������2:36:37
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
	public void readFields(DataInput in) throws IOException {
		chinese = in.readFloat();
		math = in.readFloat();
		english = in.readFloat();
		physics = in.readFloat();
		chemistry = in.readFloat();
	}

	public int compareTo(Object o) {
		
		return 0;
	}
	
}

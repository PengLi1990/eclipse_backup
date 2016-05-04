package com.buaa.wc;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author ����
 * @time 2016��1��11������4:40:12
 * 4�������У�ǰ������ָ��mapper������ݵ����ͣ�KEYIN�������key�����ͣ�VALUEIN�������value������
 * map �� reduce ������������������ key-value�Ե���ʽ��װ��
 * Ĭ������£���ܴ��ݸ����ǵ�mapper����������У�key��Ҫ������ı���һ�е���ʼƫ��������һ�е�������Ϊvalue
 * Text��ӦString LongWritable��ӦLong
 */
public class WCMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
	//mapreduce���ÿ��һ����ݾ͵���һ�θ÷���
	protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
		//����ҵ���߼���д������������У���������ҵ��Ҫ���������Ѿ�����ܴ��ݽ������ڷ����Ĳ����� key-value
		//key ����һ����ݵ���ʼƫ����     value ����һ�е��ı�����
		//����һ�е�����ת����string����
		String line = value.toString();
		//����һ�е��ı����ض��ָ����з�
		String[] words = line.split(" ");
		//������������������Ϊkv��ʽ  k������   v �� 1
		for(String w : words){
			//����һ�μ�һ��һ.
			//new Text(w),��װ���
			context.write(new Text(w), new LongWritable(1));
		}
	}
}

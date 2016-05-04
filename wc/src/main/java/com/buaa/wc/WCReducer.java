package com.buaa.wc;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer< Text, LongWritable, LongWritable, Text> {

	//�����map�������֮�󣬽�����kv�Ի������������з��飬Ȼ�󴫵�һ����<key,valus{}>������һ��reduce����
	//<hello,{1,1,1,1,1,1.....}>
	@Override
	protected void reduce(Text key, Iterable<LongWritable> v2s,Context context) throws IOException, InterruptedException {
		long counter = 0;
		//����value��list�������ۼ����
		for(LongWritable i : v2s){
			counter += i.get();
		}
		//�����һ�����ʵ�ͳ�ƽ��
		context.write(new LongWritable(counter), key);
	}
	
}

package com.buaa.hadoop1.qq;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class QqReducer extends Reducer<LongWritable, Text, Text, Text> {
	//map֮�������Ѱ�key�ֺ�����
	@Override
	protected void reduce(LongWritable key, Iterable<Text> itrator, Context context)
			throws IOException, InterruptedException {
		Set<String> set = new HashSet<String>();
		for(Text i : itrator){//itrator��װ����Text
			set.add(i.toString());
		}
		
		//��ѿ�����
		if(set.size() > 1){
			Iterator<String> i = set.iterator();
			while(i.hasNext()){
				String name = i.next();
				Iterator<String> j = set.iterator();
				while(j.hasNext()){
					String others = j.next();
					if(name.equals(others)){
						context.write(new Text(name), new Text(others));
					}
				}
			}
		}
		
	}
}

package com.buaa.hadoop1.qq;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class QqReducer extends Reducer<LongWritable, Text, Text, Text> {
	//map之后数据已按key分好组了
	/*
	 * 一个继承 Reducer 的静态类 ReduceClass： 
		该类实现了 reduce(Text key, Iterable< Text> values, Context context) 方法，reduce 方法包含三个参数：
        Text key：Map 端输出的 Key 值。
        Iterable< Text> values：Map 端输出的 Value 集合（相同 Key 的集合）。
        Context context：Reduce 端的上下文。
		reduce 方法的主要功能就是获取 map 方法的 key-value 结果，相同的 Key 发送到同一个 reduce 里处理，然后迭代 Key，把 Value 相加，结果写到 HDFS 系统里面。
	 */
	@Override
	protected void reduce(LongWritable key, Iterable<Text> itrator, Context context)
			throws IOException, InterruptedException {
		Set<String> set = new HashSet<String>();
		for(Text i : itrator){//itrator里装的是Text
			set.add(i.toString());
		}
		
		//算笛卡尔积
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

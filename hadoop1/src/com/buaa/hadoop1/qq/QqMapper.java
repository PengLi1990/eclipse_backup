package com.buaa.hadoop1.qq;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/*
 * qq好友推荐
 */
public class QqMapper extends Mapper<LongWritable, Text, Text, Text> {
	//每读一行就会调用map方法
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		 String friend = value.toString();
		 String[] f = friend.split("\t");
		 context.write(new Text(f[0]), new Text(f[1]));
		 context.write(new Text(f[1]), new Text(f[0]));
	}

}

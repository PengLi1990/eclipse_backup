package com.buaa.hadoop1.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * @author 李鹏
 * @time 2016年1月11日下午4:37:36
 */
public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance(new Configuration());
		
		//注意：一定要把main方法所在的类设置进去。因为要打成jar包
		job.setJarByClass(WordCount.class);
		
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//在hadoop的hdfs上
		FileInputFormat.setInputPaths(job, new Path("/words.txt"));
		
		job.setReducerClass(WCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		//在本地上
		FileOutputFormat.setOutputPath(job, new Path("/words111.txt"));
		
		//提交任务
		job.waitForCompletion(true);
	}

}

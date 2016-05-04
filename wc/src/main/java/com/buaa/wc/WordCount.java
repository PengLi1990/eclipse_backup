package com.buaa.wc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * @author ����
 * @time 2016��1��11������4:37:36
 */
public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance(new Configuration());
		
		//ע�⣺һ��Ҫ��main�������ڵ������ý�ȥ����ΪҪ���jar��
		job.setJarByClass(WordCount.class);
		
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//��hadoop��hdfs��
		FileInputFormat.setInputPaths(job, new Path("/words.txt"));
		
		job.setReducerClass(WCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		//�ڱ�����
		FileOutputFormat.setOutputPath(job, new Path("/words111.txt"));
		
		//�ύ����
		job.waitForCompletion(true);
	}

}

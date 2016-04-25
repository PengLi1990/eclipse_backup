package com.buaa.hadoop1.qq;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

/**
 * qq好友推荐
 * @author 李鹏
 * @time 2016年4月23日下午3:39:09
 */
public class QqRecommend {

	public static void main(String[] args) throws Exception {
		Job job = Job.getInstance();//获取实例对象
		job.setJarByClass(QqRecommend.class);//设置主类
		
		//设置Mapper类
		job.setMapperClass(QqMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//设置数据存放在hdfs上的路径,FileInputFormat它的主要作用是指出作业的输入文件位置
		FileInputFormat.setInputPaths(job, new Path("/qq.txt"));
		
		//设置Reduce类
		job.setReducerClass(QqReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//设置结果输出路径
		FileOutputFormat.setOutputPath(job, new Path("/qq_recommend.txt"));
		
		//提交任务
		job.waitForCompletion(true);
	}

}

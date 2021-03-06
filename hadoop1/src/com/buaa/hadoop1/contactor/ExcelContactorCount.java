package com.buaa.hadoop1.contactor;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 
 */
public class ExcelContactorCount extends Configured implements Tool{
	private Logger logger = LoggerFactory.getLogger(ExcelContactorCount.class);
	
	/*
	 * Mapper的任务是啥????????????????
	 */
	public static class ContactorMapper extends Mapper<LongWritable,Text,Text,Text>{
		private Logger logger = LoggerFactory.getLogger(ContactorMapper.class);
		//定义输出的键值对
		private Text pkey;
		private Text pvalue;
		
		//处理读入的每一行
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			//10, 老爸, 13999123786, 2014-12-20
			String line = value.toString();
			String[] records = line.split("\\s+");
			String[] months = records[3].split("-");
			
			pkey.set(records[1] + "\t" + months[1]); 
			pvalue.set(records[2]);
			
			context.write(pkey, pvalue);
			
		}
	}
	
	/******************************
	 * Mapper到Reducer之间要经过啥程序呢？
	 *	第一步：map。读取输入文件内容，解析成key、value对。对输入文件的每一行，解析成key、value对。
	 * 	第二步：combine。对相同的key合并。
	 *	第三步：reduce。对相同的邮箱域统计求和。
	 */

	/*
	 * Reducer的任务是啥？？？？？？？？？？？
	 */
	public static class ContactorReducer extends Reducer<Text,Text,Text,Text>{
		private Text pvalue = new Text();
		//此时的values里有啥    Map 端输出的 Value 集合（相同 Key 的集合）。
		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			Text outKey = values.iterator().next();
			for(Text t : values){
				sum++;
			}
			pvalue.set(outKey + "\t" + sum);
			context.write(key, pvalue);
		}
	}
	
	/*
	 * 自定义多文件输出格式类
	 * 为什么？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
	 */
	public static class PhoneOutputFormat extends MailMultipleOutputFormat<Text,Text>{
		@Override
		protected String generateFileNameForKeyValue(Text key, Text value, Configuration conf) {
			String[] records = key.toString().split("\t");
			return records[1] + ".txt";
			
		}
		
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();// 配置文件对象
		Path mypath = new Path(args[1]);
		FileSystem hdfs = mypath.getFileSystem(conf);// 创建输出路径
		if (hdfs.isDirectory(mypath)) {
			hdfs.delete(mypath, true);
		}
		logger.info("Driver started");

		Job job = new Job();
		job.setJarByClass(ExcelContactorCount.class);
		job.setJobName("Excel Record Reader");
		job.setMapperClass(ContactorMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setInputFormatClass(ExcelIputFormat.class);//自定义输入格式
		
		job.setReducerClass(ContactorReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setOutputFormatClass(PhoneOutputFormat.class);//自定义输出格式
		

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		String[] args0 = { 
				"hdfs://lp1:9000/contact/phone.xls",
				"hdfs://lp1:9000/contact/phone-out/" 
				};
		int ec = ToolRunner.run(new Configuration(), new ExcelContactorCount(), args0);
		System.exit(ec);
	}
}

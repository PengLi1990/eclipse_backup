package com.buaa.weibo;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 统计出微博被关注最多的前10名
 * @author 李鹏
 * @time 2016年5月6日下午10:26:29
 */
public class WeiBo extends Configured implements Tool{
	private static Logger log = Logger.getLogger(WeiBo.class.getCanonicalName());
	/*
	 * 第一个Map任务：
	 * 	解析json数据，统计每个微博被关注数
	 */
	public static class Map extends Mapper<Object,Text,Text,IntWritable>{
		//实现序列化的计数器
		private IntWritable one = new IntWritable(1);
		
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();//将每一行转化为字符串
			JSONObject json = new JSONObject(line);
			String wid = json.getString("id");
			JSONArray ids = json.getJSONArray("ids");
			
			for(int i=0;i<ids.length();i++){
				String follower = ids.getString(i);
				context.write(new Text(follower), one);
			}
			
		}
	}

	/*
	 * 对每个微博的关注数进行统计
	 */
	public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>{
		//通过writabe的get/set方法实现基本类型与序列化的转换
		private IntWritable result = new IntWritable();
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int sum = 0;
			for(IntWritable i : values){
				sum = sum + i.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}
	
	/*
	 * 第二个Map，处理掉排名10以后的数据
	 */
	public static class TopMap extends Mapper<Object,Text,IntWritable,Text>{
		//输出微博关注数
		IntWritable outKey = new IntWritable();
		//输出对应的微博号
		Text outValue = new Text();
		//排名前k位的
		private static final int k = 10;
		
		// 用TreeMap存储可以利用它的排序功能
		// 这里用 MyInt 因为TreeMap是对key排序，且不能唯一，而关注数可能相同，要以关注数为Key就必需对它封装
		private static TreeMap<MyInt,String> tm =  new TreeMap<MyInt,String>(new Comparator<MyInt>() {
			//默认是从小到大的顺序排的，现在修改为从大到小
			public int compare(MyInt o1, MyInt o2) {
				
				return o2.compareTo(o1);
			}
		});
		
		
		
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, Text>.Context context)
				throws IOException, InterruptedException {
			String[] tokens = value.toString().split("\\s+");//\s  匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [ \f\n\r\t\v]。     + 匹配前面的子表达式一次或多次
			// TreeMap 添加关注数为key，微博号为value
			tm.put(new MyInt(Integer.parseInt(tokens[1])), tokens[0]);
			//TreeMap以对内部数据进行了排序，最后一个必定是最小的
			if(tm.size()>k){
				tm.remove(tm.lastKey());//过滤10以后的数据
			}
		}
		
		/*
		 * 任务结束时调用
		 */
		@Override
		protected void cleanup(Mapper<Object, Text, IntWritable, Text>.Context context)
				throws IOException, InterruptedException {
			//返回此映射中包含的映射关系的 Set 视图。该 set 的迭代器将按升序返回这些条目。
			Set<Entry<MyInt, String>> set = tm.entrySet();
			// 将Map结果输出 key=关注数，value=微博号
			for (Entry<MyInt, String> entry : set) {
				context.write(new IntWritable(entry.getKey().getValue()),
						new Text(entry.getValue()));
			}
		}
	}
	
	/*
	 * 合并数据，统计排名前10位的微博，然后输出到自定义路径下
	 */
	public static class TopReduce extends Reducer<IntWritable, Text,Text,IntWritable>{
		
		private static MultipleOutputs<Text, IntWritable> mos = null;

		// 要获得前K个关注数最高的词
		private static final int k = 10;

		// 用TreeMap存储可以利用它的排序功能
		// 这里用 MyInt 因为TreeMap是对key排序，且不能唯一，而关注数可能相同，要以关注数为Key就必需对它封装
		private static TreeMap<MyInt, String> tm = new TreeMap<MyInt, String>(
				new Comparator<MyInt>() {
					 // 默认是从小到大的顺序排的，现在修改为从大到小
					public int compare(MyInt o1, MyInt o2) {
						return o2.compareTo(o1);
					}

				});

		/*
		 * 以关注数为Key是要用到reduce的排序功能
		 */
		@Override
		protected void reduce(IntWritable key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {
			for (Text text : values) {
				tm.put(new MyInt(key.get()), text.toString());

				// TreeMap以对内部数据进行了排序，最后一个必定是最小的
				if (tm.size() > k) {
					tm.remove(tm.lastKey());
				}

			}
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			mos = new MultipleOutputs<Text, IntWritable>(context);
			Set<Entry<MyInt, String>> set = tm.entrySet();
			// 将排名前10位的微博号输出到自定义路径下
			for (Entry<MyInt, String> entry : set) {
				mos.write("topKMOS", new Text(entry.getValue()),
						new IntWritable(entry.getKey().getValue()));
			}
			mos.close();
		}
	}
	public int run(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
				/**
				 * 第一个MapReduce任务 统计微博关注数
				 */
				Configuration conf = new Configuration();
				Path out = new Path(args[1]);
				Path out2 = new Path(args[2]);
				FileSystem fs = out.getFileSystem(conf);
				if (fs.isDirectory(out)) {
					fs.delete(out, true);
				}
				if (fs.isDirectory(out2)) {
					fs.delete(out2, true);
				}
				Job job = new Job(conf, "WeiBo");
				job.setJarByClass(WeiBo.class);

				job.setMapperClass(Map.class);
				job.setCombinerClass(Reduce.class);
				job.setReducerClass(Reduce.class);

				job.setMapOutputKeyClass(Text.class);
				job.setMapOutputValueClass(IntWritable.class);

				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(IntWritable.class);

				FileInputFormat.addInputPath(job, new Path(args[0]));
				FileOutputFormat.setOutputPath(job, new Path(args[1]));

				/**
				 * 第二个MapReduce任务 统计排名前10的微博关注数
				 */
				Configuration conf2 = new Configuration();
				Job job2 = new Job(conf2, "WeiBo");
				job2.setJarByClass(WeiBo.class);
				job2.setMapperClass(TopMap.class);
				job2.setReducerClass(TopReduce.class);

				job2.setMapOutputKeyClass(IntWritable.class);
				job2.setMapOutputValueClass(Text.class);

				job2.setOutputKeyClass(Text.class);
				job2.setOutputValueClass(IntWritable.class);

				FileInputFormat.addInputPath(job2, new Path(args[1]));
				FileOutputFormat.setOutputPath(job2, new Path(args[2]));

				// 这里利用MultipleOutputs进行对文件输出
				MultipleOutputs.addNamedOutput(job2, "topKMOS", TextOutputFormat.class,
						Text.class, Text.class);

				// 构造一个 cJob1
				ControlledJob cJob1 = new ControlledJob(conf);
				// 设置 MapReduce job1
				cJob1.setJob(job);

				// 构造一个 cJob2
				ControlledJob cJob2 = new ControlledJob(conf2);
				// 设置 MapReduce job2
				cJob2.setJob(job2);

				cJob2.addDependingJob(cJob1);// cjob2依赖cjob1

				// 定义job管理对象
				JobControl jobControl = new JobControl("12");

				// 把两个构造的job加入到JobControl中
				jobControl.addJob(cJob1);
				jobControl.addJob(cJob2);

				// 启动线程运行任务
				Thread t = new Thread(jobControl);
				t.start();
				while (true) {
					if (jobControl.allFinished()) {
						jobControl.stop();
						break;
					}

				}
				// job.waitForCompletion(true);// 提交任务
				return 0;
	}
	
	public static void main(String[] args) throws Exception {
		String[] args0 = {
				"hdfs://lp1:9000/middle/weibo/relsemple.json",
				"hdfs://lp1:9000/middle/weibo/out",
				"hdfs://lp1:9000/middle/weibo/topkout" };
		int ec = ToolRunner.run(new Configuration(), new WeiBo(), args0);
		System.exit(ec);
	}

}

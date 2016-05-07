package com.buaa.weibo;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
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
		
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, Text>.Context context)
				throws IOException, InterruptedException {
			
			// 用TreeMap存储可以利用它的排序功能
			// 这里用 MyInt 因为TreeMap是对key排序，且不能唯一，而关注数可能相同，要以关注数为Key就必需对它封装
			TreeMap<MyInt,String> tm =  new TreeMap<MyInt,String>(new Comparator<MyInt>() {
				//默认是从小到大的顺序排的，现在修改为从大到小
				public int compare(MyInt o1, MyInt o2) {
					
					return o2.compareTo(o1);
				}
			});
			
		}
	}
	public int run(String[] arg0) throws Exception {
		
		return 0;
	}

}

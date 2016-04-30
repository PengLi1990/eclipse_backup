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
	/*
	 * 一个继承 Mapper 的静态类 MapClass：
		该类实现了 map(Text key,Text value,Context context)方法，map 方法包含三个参数：
        Text key：每行文件的 key 值（即引用的专利）。
        Text value：每行文件的 value 值（即被引用的专利）。
        Context context：Map 端的上下文。
		map 方法主要就是把字符串解析成 Key-Value的形式，发给 Reduce 端来统计。
		需要注意：此任务中的文件输入格式为 KeyValueTextInputFormat，所以 map 方法可以直接将 key/value 作为输出结果。
	 */
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		 String friend = value.toString();
		 String[] f = friend.split("\t");
		 context.write(new Text(f[0]), new Text(f[1]));
		 context.write(new Text(f[1]), new Text(f[0]));
	}

}

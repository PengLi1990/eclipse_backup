package com.buaa.hadoop1.score;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/**
 * 成绩统计类
 * @author 李鹏
 * @time 2016年4月25日下午3:48:09
 */
public class ScoreCount extends Configured implements Tool {
	
	/*
	 * Map阶段
	 */
	public static class ScoreMapper extends Mapper<Text,ScoreWritable,Text,ScoreWritable>{

		@Override
		protected void map(Text key, ScoreWritable value,Context context)
						throws IOException, InterruptedException {
			context.write(key, value);
		}
		
	}
	
	/*
	 * Reduce阶段
	 */
	public static class ScoreReducer extends Reducer<Text,ScoreWritable,Text,Text>{
		private Text text = new Text();
		
		@Override
		protected void reduce(Text key, Iterable<ScoreWritable> values,
				Context context) throws IOException, InterruptedException {
			float totalScore = 0.0f;
			float averageScore = 0.0f;
			for(ScoreWritable s : values){
				totalScore += s.getChinese() + s.getMath() + s.getEnglish() + s.getChemistry() + s.getPhysics();
				averageScore += totalScore/5;
			}
			
			text.set(totalScore + "\t" + averageScore);
			context.write(key, text);
		}
		
	}
	
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();//读取配置文件
		Path path = new Path(args[1]);
		FileSystem hdfs = path.getFileSystem(conf);//创建输出路径
		if(hdfs.isDirectory(path)){
			hdfs.delete(path, true);
		}
		
		Job job = new Job(conf,"ScoreCount");//新建任务
		job.setJarByClass(ScoreCount.class);//设置主类
		
		FileInputFormat.addInputPath(job, new Path(args[0]));//输入路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));//输出路径
		
		job.setMapperClass(ScoreMapper.class);//Mapper
		job.setReducerClass(ScoreReducer.class);//Reducer
		
		job.setMapOutputKeyClass(Text.class);//Map key输出类型
		job.setMapOutputValueClass(ScoreWritable.class);//Map value输出类型
		
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(ScoreWritable.class);
		job.setInputFormatClass(ScoreInputFormat.class);
		//ScoreInputFormat.setInputPaths(job, inputPaths);
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		String[] arg0 = {
				"hdfs://lp1:9000/score/score.txt",
				"hdfs://lp1:9000/score/output/"
		};
		
		int ec = ToolRunner.run(new Configuration(),new ScoreCount(), arg0);
		System.exit(ec);
	}

}

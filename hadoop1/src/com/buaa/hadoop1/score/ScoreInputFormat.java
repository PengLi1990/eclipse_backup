package com.buaa.hadoop1.score;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;
/*
 *    InputFormat 接口决定了输入文件如何被 Hadoop 分块（split up）与接受。
 */


 /**
  * 自定义学生成绩读写InputFormat
  * 数据格式参考：19020090017 小讲 90 99 100 89 95
  * @author 李鹏
  * @time 2016年4月25日下午2:58:39
  */
public class ScoreInputFormat extends FileInputFormat<Text, ScoreWritable> {

	// getSplits(JobContext context) 方法负责将一个大数据逻辑分成许多片。
	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}

	@Override
	public RecordReader<Text, ScoreWritable> createRecordReader(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		
		return new ScoreRecordReader();
	}
	
	 //RecordReader 中的两个参数分别填写我们期望返回的key/value类型，我们期望key为Text类型，
	 //value为ScoreWritable类型封装学生所有成绩
	public static class ScoreRecordReader extends RecordReader<Text, ScoreWritable>{

		public LineReader in;//行读取器
		public Text lineKey;//自定义key类型
		public ScoreWritable lineValue;//自定义value类型
		public Text line;//每行数据类型
		
		@Override
		public void close() throws IOException {
			if(in!=null){
				in.close();
			}
		}

		@Override
		public Text getCurrentKey() throws IOException, InterruptedException {
			
			return lineKey;
		}

		@Override
		public ScoreWritable getCurrentValue() throws IOException, InterruptedException {
			
			return lineValue;
		}

		@Override
		public float getProgress() throws IOException, InterruptedException {
			
			return 0;
		}

		@Override
		public void initialize(InputSplit input, TaskAttemptContext context) throws IOException, InterruptedException {
			FileSplit split = (FileSplit) input;
			Configuration job = context.getConfiguration();
			Path file = split.getPath();
			FileSystem fs = file.getFileSystem(job);
			FSDataInputStream filein = fs.open(file);
			in = new LineReader(filein,job);
			line = new Text();
			lineKey = new Text();
			lineValue = new ScoreWritable();
		}

		//此方法读取每行数据，完成自定义的key和value
		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			int lineSize = in.readLine(line);
			if(lineSize == 0){
				return false;
			}
			String[] piece = line.toString().split("\\s+");
			if(piece.length != 7){
				throw new IOException("读到了无效数据！");
			}
			//将成绩转换成float
			float a,b,c,d,e;
			a = Float.parseFloat(piece[2].trim());
			b = Float.parseFloat(piece[3].trim());
			c = Float.parseFloat(piece[4].trim());
			d = Float.parseFloat(piece[5].trim());
			e = Float.parseFloat(piece[6].trim());
			
			lineKey.set(piece[0] + "\t" + piece[1]);
			lineValue.set(a,b,c,d,e);
			
			return true;
		}
		
	}

}

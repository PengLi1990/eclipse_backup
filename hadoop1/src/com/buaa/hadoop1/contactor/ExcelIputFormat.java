package com.buaa.hadoop1.contactor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class ExcelIputFormat extends FileInputFormat<LongWritable, Text> {

	//这里默认的是系统实现的RecordReader，按行读取。我们要自定义这个类
	@Override
	public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		
		return new ExcelRecordReader();
	}
	/******************************
	 * The record reader breaks the data into key/value pairs for input to the Mapper
	 * record用来拆分数据
	 * key:?????????????????????????????????????????????????????????????????????????????
	 * value:
	 */
	public class ExcelRecordReader extends RecordReader<LongWritable, Text>{
		private LongWritable key;
		private Text value;
		private InputStream is;
		private String[] strArrayOfLines;

		/**
		 * split:the split that defines the range of records to readcontext the information about the task
		 * context:the information about the task
		 */
		@Override
		public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
			//FileSplit是InputSplit的子类，强转为子类是为了增强功能，使用子类的属性方法
			FileSplit split = (FileSplit)inputSplit;//每个map要处理的块
			Configuration job = context.getConfiguration();
			//path代表文件或文件路径
			Path file = split.getPath();//The file containing this split's data.
			FileSystem fs = file.getFileSystem(job);//返回拥有这个路径的FileSystem，有了FileSystem就可以操作hdfs了
			FSDataInputStream filein = fs.open(file);//打开文件流
			is = filein;//连接数据流
			String line = new ExcelParser().parseExcelData(is);//解析excel文档
			strArrayOfLines = line.split("\n");//?????
		}

		//读取下一组键值对
		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			if(key == null){
				key = new LongWritable(0);//?????????
				value = new Text(strArrayOfLines[0]);
			}else{
				if(key.get()<this.strArrayOfLines.length-1){
					long pos = (int)key.get();
					key.set(pos+1);//读下一组
					value.set(this.strArrayOfLines[(int)(pos+1)]);
					pos++;
				}else{
					return false;
				}
			}
			
			if(key == null || value == null){
				return false;
				
			}else{
				return true;
				
			}
		}

		@Override
		public LongWritable getCurrentKey() throws IOException, InterruptedException {
			
			return key;
		}

		@Override
		public Text getCurrentValue() throws IOException, InterruptedException {
			
			return value;
		}

		@Override
		public float getProgress() throws IOException, InterruptedException {
			
			return 0;
		}

		//读完的时候会调用
		@Override
		public void close() throws IOException {
			if(is != null){
				is.close();
			}
		}
		
	}

}

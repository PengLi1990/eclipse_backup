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

	//����Ĭ�ϵ���ϵͳʵ�ֵ�RecordReader�����ж�ȡ������Ҫ�Զ��������
	@Override
	public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		
		return new ExcelRecordReader();
	}
	/******************************
	 * The record reader breaks the data into key/value pairs for input to the Mapper
	 * record�����������
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
			//FileSplit��InputSplit�����࣬ǿתΪ������Ϊ����ǿ���ܣ�ʹ����������Է���
			FileSplit split = (FileSplit)inputSplit;//ÿ��mapҪ�����Ŀ�
			Configuration job = context.getConfiguration();
			//path�����ļ����ļ�·��
			Path file = split.getPath();//The file containing this split's data.
			FileSystem fs = file.getFileSystem(job);//����ӵ�����·����FileSystem������FileSystem�Ϳ��Բ���hdfs��
			FSDataInputStream filein = fs.open(file);//���ļ���
			is = filein;//����������
			String line = new ExcelParser().parseExcelData(is);//����excel�ĵ�
			strArrayOfLines = line.split("\n");//?????
		}

		//��ȡ��һ���ֵ��
		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			if(key == null){
				key = new LongWritable(0);//?????????
				value = new Text(strArrayOfLines[0]);
			}else{
				if(key.get()<this.strArrayOfLines.length-1){
					long pos = (int)key.get();
					key.set(pos+1);//����һ��
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

		//�����ʱ������
		@Override
		public void close() throws IOException {
			if(is != null){
				is.close();
			}
		}
		
	}

}
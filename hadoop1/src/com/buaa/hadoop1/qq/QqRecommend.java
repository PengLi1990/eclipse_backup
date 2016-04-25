package com.buaa.hadoop1.qq;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

/**
 * qq�����Ƽ�
 * @author ����
 * @time 2016��4��23������3:39:09
 */
public class QqRecommend {

	public static void main(String[] args) throws Exception {
		Job job = Job.getInstance();//��ȡʵ������
		job.setJarByClass(QqRecommend.class);//��������
		
		//����Mapper��
		job.setMapperClass(QqMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//�������ݴ����hdfs�ϵ�·��,FileInputFormat������Ҫ������ָ����ҵ�������ļ�λ��
		FileInputFormat.setInputPaths(job, new Path("/qq.txt"));
		
		//����Reduce��
		job.setReducerClass(QqReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//���ý�����·��
		FileOutputFormat.setOutputPath(job, new Path("/qq_recommend.txt"));
		
		//�ύ����
		job.waitForCompletion(true);
	}

}

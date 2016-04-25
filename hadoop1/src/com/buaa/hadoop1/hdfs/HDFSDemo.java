package com.buaa.hadoop1.hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
/**
 * @author ����
 * @time 2016��1��11������3:02:02
 */
public class HDFSDemo {
	
	FileSystem fs = null;
	
	@Before
	public void init() throws Exception{
		//FileSystem�ǳ����࣬ͨ��get������ȡ��ʵ����
		fs = FileSystem.get(new URI("hdfs://lp1:9000"), new Configuration(),"root");
	}
	
	/**
	 * �ϴ�������Ȩ������
	 * @throws Exception
	 */
	@Test
	public void testUpload() throws Exception{
		//�ӱ��ض�������new F
		InputStream in = new FileInputStream("c://1.txt");
		//��hdfs��д
		OutputStream out = fs.create(new Path("/words.txt"));
		//�ӱ���д��hdfs
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	@Test
	public void testDownload() throws Exception{
		//��һ������������hdfs�϶�ȡjdk1.7������
		InputStream is = fs.open(new Path("/words111.txt"));
		OutputStream out = new FileOutputStream("c://words111.txt");
		//��in�����out
		IOUtils.copyBytes(is, out, 4096,true);
	}

	@Test
	public void testDownload1() throws Exception{
		fs.copyToLocalFile(new Path("/AdobeDreamweaverCS6_gr.zip"), new Path("c://cs6.zip"));
	}
	
	@Test
	public void testDel() throws IllegalArgumentException, IOException{
		//true false�Ƿ�ݹ�ɾ��
		fs.delete(new Path("/words111.txt"), false);
	}
	
	@Test
	public void testMkdir() throws IllegalArgumentException, IOException{
		fs.mkdirs(new Path("/java"));
	}
}

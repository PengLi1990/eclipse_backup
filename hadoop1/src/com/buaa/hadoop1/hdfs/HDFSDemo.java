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
 * @author 李鹏
 * @time 2016年1月11日下午3:02:02
 */
public class HDFSDemo {
	
	FileSystem fs = null;
	
	@Before
	public void init() throws Exception{
		//FileSystem是抽象类，通过get方法获取其实现类
		fs = FileSystem.get(new URI("hdfs://lp1:9000"), new Configuration(),"root");
	}
	
	/**
	 * 上传会遇到权限问题
	 * @throws Exception
	 */
	@Test
	public void testUpload() throws Exception{
		//从本地读，故用new F
		InputStream in = new FileInputStream("c://1.txt");
		//往hdfs上写
		OutputStream out = fs.create(new Path("/words.txt"));
		//从本地写到hdfs
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	@Test
	public void testDownload() throws Exception{
		//打开一个输入流，从hdfs上读取jdk1.7到本地
		InputStream is = fs.open(new Path("/words111.txt"));
		OutputStream out = new FileOutputStream("c://words111.txt");
		//把in输出给out
		IOUtils.copyBytes(is, out, 4096,true);
	}

	@Test
	public void testDownload1() throws Exception{
		fs.copyToLocalFile(new Path("/AdobeDreamweaverCS6_gr.zip"), new Path("c://cs6.zip"));
	}
	
	@Test
	public void testDel() throws IllegalArgumentException, IOException{
		//true false是否递归删除
		fs.delete(new Path("/words111.txt"), false);
	}
	
	@Test
	public void testMkdir() throws IllegalArgumentException, IOException{
		fs.mkdirs(new Path("/java"));
	}
}

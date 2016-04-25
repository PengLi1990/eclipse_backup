package com.buaa.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	
	public static void main(String[] args) throws Exception {
		//配置文件
		Properties properties = new Properties();
		properties.put("zk.connect", "lp5:2181,lp6:2181,lp7:2181");
		properties.put("metadata.broker.list","lp5:9092,lp6:9092,lp7:9092");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig config = new ProducerConfig(properties);
		Producer<String, String> producer = new Producer<String, String>(config);
		
		//处理业务
		//读取文件 读取数据库 读取内存
		for(int i=0;i<100;i++){
			Thread.sleep(500);
			KeyedMessage<String, String> message = new KeyedMessage<String, String>("order", "" + i, "10086" + i*i);
			producer.send(message);
		}
	}
}

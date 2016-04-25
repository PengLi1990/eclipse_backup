package com.buaa.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
/*
 * 读取消息
 */
public class ComsumerDemo {

	private static final String topic = "order";
	private static final Integer threads = 1;
	
	public static void main(String[] args) {
		//配置文件
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "lp5:2181,lp6:2181,lp7:2181");
		properties.put("group.id", "1111");//确定分组的id，因为会有不同的分区
		properties.put("auto.offset.reset", "smallest");
		
		ConsumerConfig config = new ConsumerConfig(properties);
		ConsumerConnector consumer =Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, 1);
//		topicCountMap.put("mygirls", 1);
//		topicCountMap.put("myboys", 1);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get("order");
		
		for(final KafkaStream<byte[], byte[]> kafkaStream : streams){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(MessageAndMetadata<byte[], byte[]> mm : kafkaStream){
						String msg = new String(mm.message());
						System.out.println(msg);
					}
				}
			
			}).start();
		}
		
	}

}

package com.buaa.storm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
/**
 * 组织各个处理组件形成一个完整的处理流程，就是所谓的Topology
 * 并且将该Topology提交给storm集群去运行，Topology提交到集群后就会永无休止地去执行
 * @author 李鹏
 * @time 2016年4月12日下午3:50:20
 */
public class TopoMain {

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();
		//将spout设置到topology中
		//parallelism_hint:4 表示用4个excutor来执行这个组件
		//setNumTasks(8)，设置该组件执行时并发的task数量，也就意味着1个excutor会执行2个task
		builder.setSpout("randomspout", new RandomWordSpout(),4).setNumTasks(8);
		//将bolt设置到topology中，并且指定他接收randomspout组件的消息
		builder.setBolt("upperjob", new HandlerBolt(),4).shuffleGrouping("randomspout");
		//将bolt设置到topology中，并且指定他接收upperjob组件的消息
		builder.setBolt("datejob", new MoreBolt(), 4).shuffleGrouping("upperjob");
		//用builder来创建topology
		StormTopology topology = builder.createTopology();
		
		//配置一些topology在集群中运行的参数
		Config conf = new Config();
		conf.setNumWorkers(4);
		conf.setDebug(true);
		conf.setNumAckers(0);
		
		//conf.set("hbase.zookeeper.quorum", "lp5,lp6,lp7");
		
		//提交
		StormSubmitter.submitTopology("demo3", conf, topology);
	}

}

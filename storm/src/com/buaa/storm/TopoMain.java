package com.buaa.storm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
/**
 * ��֯������������γ�һ�������Ĵ������̣�������ν��Topology
 * ���ҽ���Topology�ύ��storm��Ⱥȥ���У�Topology�ύ����Ⱥ��ͻ�������ֹ��ȥִ��
 * @author ����
 * @time 2016��4��12������3:50:20
 */
public class TopoMain {

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		TopologyBuilder builder = new TopologyBuilder();
		//��spout���õ�topology��
		//parallelism_hint:4 ��ʾ��4��excutor��ִ��������
		//setNumTasks(8)�����ø����ִ��ʱ������task������Ҳ����ζ��1��excutor��ִ��2��task
		builder.setSpout("randomspout", new RandomWordSpout(),4).setNumTasks(8);
		//��bolt���õ�topology�У�����ָ��������randomspout�������Ϣ
		builder.setBolt("upperjob", new HandlerBolt(),4).shuffleGrouping("randomspout");
		//��bolt���õ�topology�У�����ָ��������upperjob�������Ϣ
		builder.setBolt("datejob", new MoreBolt(), 4).shuffleGrouping("upperjob");
		//��builder������topology
		StormTopology topology = builder.createTopology();
		
		//����һЩtopology�ڼ�Ⱥ�����еĲ���
		Config conf = new Config();
		conf.setNumWorkers(4);
		conf.setDebug(true);
		conf.setNumAckers(0);
		
		//conf.set("hbase.zookeeper.quorum", "lp5,lp6,lp7");
		
		//�ύ
		StormSubmitter.submitTopology("demo3", conf, topology);
	}

}

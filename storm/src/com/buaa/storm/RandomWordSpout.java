package com.buaa.storm;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomWordSpout extends BaseRichSpout {
	/**
	 * ����Դ
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	String[] str = new String[]{"cloud","web","android","ios","java","bigdata","linux"};
	
	/*
	 * ��ʼ����������spout���ʵ����ʱ����һ��
	 */
	@Override
	public void open(Map map, TopologyContext tc, SpoutOutputCollector collector) {
		this.collector = collector;
	}
	
	//���ϵ�����һ���������tuple��Ϣ
	//�������Ǹ�spout����ĺ����߼�
	@Override
	public void nextTuple() {
		//���Դ�kafka��Ϣ�������õ�����,�����������Ǵ�str�����������ѡһ�����ͳ�ȥ
		Random r = new Random();
		int index = r.nextInt(str.length);
		String job = str[index];
		//����Ϣ��װ��tuple��������Ϣ����һ�����
		collector.emit(new Values(job));
		//ÿ����һ����Ϣ������500ms
		Utils.sleep(500);
	}

	

	//������spout������ͳ�ȥ��tuple�е����ݵ��ֶ���
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("orignname"));
	}

}

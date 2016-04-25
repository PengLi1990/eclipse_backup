package com.buaa.storm;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class MoreBolt extends BaseBasicBolt {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FileWriter fileWriter = null;
	
	//��bolt������й�����ֻ�ᱻ����һ��
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		try {
			fileWriter = new FileWriter("/home/hadoop/storm/" + UUID.randomUUID());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	//��bolt����ĺ��Ĵ����߼�
	//ÿ�յ�һ��tuple��Ϣ���ͻᱻ����һ��
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(date);
		String job_upper = tuple.getString(0);
		String jbo_date = job_upper + d;
		try {
			fileWriter.write(jbo_date);
			fileWriter.write("\n");
			fileWriter.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	//��bolt�Ѿ�����Ҫ����tuple��Ϣ����һ����������Բ���Ҫ������tuple���ֶ�
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		
	}

}

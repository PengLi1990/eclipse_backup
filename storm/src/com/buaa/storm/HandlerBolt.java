package com.buaa.storm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
/**
 * �����͹�������Ϣ
 * @author ����
 * @time 2016��4��12������10:51:39
 */
public class HandlerBolt extends BaseBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//ҵ�����߼�
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String job = tuple.getString(0);//��ȡnextTuple()����emit()����������
		String job_upper = job.toUpperCase();
		
		collector.emit(new Values(job_upper));//������Ϻ�����һ������
	}

	//������bolt���Ҫ����ȥ��tuple���ֶ�
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("job_upper"));
	}

	

}

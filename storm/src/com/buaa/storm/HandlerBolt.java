package com.buaa.storm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
/**
 * 处理发送过来的信息
 * @author 李鹏
 * @time 2016年4月12日上午10:51:39
 */
public class HandlerBolt extends BaseBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//业务处理逻辑
	@Override
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String job = tuple.getString(0);//获取nextTuple()方法emit()过来的数据
		String job_upper = job.toUpperCase();
		
		collector.emit(new Values(job_upper));//处理完毕后向下一级发送
	}

	//声明该bolt组件要发出去的tuple的字段
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("job_upper"));
	}

	

}

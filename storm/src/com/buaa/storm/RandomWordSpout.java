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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	String[] str = new String[]{"cloud","web","android","ios","java","bigdata","linux"};
	
	//不断地往下一个组件发送tuple消息
	//这里面是该spout组件的核心逻辑
	@Override
	public void nextTuple() {
		//可以从kafka消息队列中拿到数据,简便起见，我们从str数组中随机挑选一个发送出去
		Random r = new Random();
		int index = r.nextInt(str.length);
		String job = str[index];
		//将信息封装成tuple，发送消息给下一个组件
		collector.emit(new Values(job));
		//每发送一个消息，休眠500ms
		Utils.sleep(500);
	}

	/*
	 * 初始化方法，在spout组件实例化时调用一次
	 * (non-Javadoc)
	 * @see backtype.storm.spout.ISpout#open(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.spout.SpoutOutputCollector)
	 */
	@Override
	public void open(Map map, TopologyContext tc, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	//声明本spout组件发送出去的tuple中的数据的字段名
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("orignname"));
	}

}

package com.buaa.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

public class HbaseDemo {
	private Configuration conf = null;
	
	@Before
	public void init(){
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "lp5,lp6,lp7");
	}
	
	/*
	 * 新建表
	 */
	@Test
	public void create() throws Exception{
		//Provides an interface to manage HBase database table metadata + general administrative functions. Use HBaseAdmin to create, drop, list, enable and disable tables. Use it also to add and drop table column families.
		//See HTable to add, update, and delete data from an individual table.
		//Currently HBaseAdmin instances are not expected to be long-lived. For example, an HBaseAdmin instance will not ride over a Master restart.
		//操纵hbase的客户端
		HBaseAdmin client = new HBaseAdmin(conf);
		//表名
		TableName tableName = TableName.valueOf("production");
		//表描述
		HTableDescriptor htd = new HTableDescriptor(tableName);
		//列族描述
		HColumnDescriptor base_info = new HColumnDescriptor("base_info");
		base_info.setMaxVersions(5);
		HColumnDescriptor external_info = new HColumnDescriptor("external_info");
		external_info.setMaxVersions(5);
		
		//添加列族
		htd.addFamily(base_info);
		htd.addFamily(external_info);
		//建表
		client.createTable(htd);
		client.close();
	}
	
	/*
	 * 删除表结构
	 */
	@Test
	public void drop() throws Exception{
		HBaseAdmin client = new HBaseAdmin(conf);
		client.disableTable("production");
		client.deleteTable("production");
		client.close();
	}
	
	/*
	 * 向表中添加数据
	 */
	@Test
	public void insert() throws Exception{
		//1.这种方法线程不安全
		//得到表
		HTable table = new HTable(conf, "production");
		//指定一行
		Put put = new Put(Bytes.toBytes("p_computer_0001"));
		//插入值
		put.add("base_info".getBytes(),"name".getBytes(),"Hongji".getBytes());
		put.add("base_info".getBytes(),"price".getBytes(),"3890".getBytes());
		put.add("external_info".getBytes(),"image".getBytes(),"nothing".getBytes());
		table.put(put);
		table.close();
		
		
		/*HConnection conn = HConnectionManager.getConnection(conf);
		HTableInterface table = conn.getTable("production");
		Put put = new Put(Bytes.toBytes("p_computer_0001"));
		put.add("base_info".getBytes(),"name".getBytes(),"Apple".getBytes());
		table.put(put);
		table.close();*/
	}
	
	/*
	 * 删除表中数据
	 * 但是删除新版本的数据后，旧版本的就顶上来了，咋个办？？？、、、
	 */
	@Test
	public void delete() throws Exception{
		HTable table = new HTable(conf,"production");
		//单行删除
		Delete del = new Delete(Bytes.toBytes("p_computer_0001"));
		del.deleteColumn(Bytes.toBytes("base_info"), Bytes.toBytes("name"));
		table.delete(del);
		table.close();
	}
	
	/*
	 * 查找数据
	 */
	@Test
	public void get() throws IOException{
		HTable table = new HTable(conf,"production");
		Get get = new Get(Bytes.toBytes("p_computer_0001"));
		get.setMaxVersions(1);//设定要查几个版本的数据
		Result result = table.get(get);
		List<Cell> cells = result.listCells();
		
		for(KeyValue kv : result.list()){
			String family = new String(kv.getFamily());//获取列族
			System.out.println(family);
			String qualifier = new String(kv.getQualifier());
			System.out.println(qualifier);
			System.out.println(new String(kv.getValue()));
		}
		table.close();
	}
	
	/*
	 * 过滤查找数据
	 */
	@Test
	public void scan() throws IOException{
		HTable table = new HTable(conf,"production");
		Scan scan = new Scan(Bytes.toBytes("p_computer_0001"), Bytes.toBytes("p_computer_0003"));
		
		//前缀过滤器----针对行键
		Filter filter = new PrefixFilter(Bytes.toBytes("p"));
		
		//行过滤器
		ByteArrayComparable rowComparator = new BinaryComparator(Bytes.toBytes("p_computer_0001"));
		RowFilter rf = new RowFilter(CompareOp.LESS_OR_EQUAL, rowComparator);
		
		/**
         * 假设rowkey格式为：创建日期_发布日期_ID_TITLE
         * 目标：查找  发布日期  为  2014-12-21  的数据
         */
        rf = new RowFilter(CompareOp.EQUAL , new SubstringComparator("_2014-12-21_"));
		
		
		//单值过滤器 1 完整匹配字节数组
		new SingleColumnValueFilter("base_info".getBytes(), "name".getBytes(), CompareOp.EQUAL, "zhangsan".getBytes());
		//单值过滤器2 匹配正则表达式
		ByteArrayComparable comparator = new RegexStringComparator("zhang.");
		new SingleColumnValueFilter("info".getBytes(), "NAME".getBytes(), CompareOp.EQUAL, comparator);

		//单值过滤器2 匹配是否包含子串,大小写不敏感
		comparator = new SubstringComparator("wu");
		new SingleColumnValueFilter("info".getBytes(), "NAME".getBytes(), CompareOp.EQUAL, comparator);

		//键值对元数据过滤-----family过滤----字节数组完整匹配
        FamilyFilter ff = new FamilyFilter(
                CompareOp.EQUAL , 
                new BinaryComparator(Bytes.toBytes("base_info"))   //表中不存在inf列族，过滤结果为空
                );
        //键值对元数据过滤-----family过滤----字节数组前缀匹配
        ff = new FamilyFilter(
                CompareOp.EQUAL , 
                new BinaryPrefixComparator(Bytes.toBytes("inf"))   //表中存在以inf打头的列族info，过滤结果为该列族所有行
                );
		
        
       //键值对元数据过滤-----qualifier过滤----字节数组完整匹配
        
        filter = new QualifierFilter(
                CompareOp.EQUAL , 
                new BinaryComparator(Bytes.toBytes("na"))   //表中不存在na列，过滤结果为空
                );
        filter = new QualifierFilter(
                CompareOp.EQUAL , 
                new BinaryPrefixComparator(Bytes.toBytes("na"))   //表中存在以na打头的列name，过滤结果为所有行的该列数据
        		);
		
        //基于列名(即Qualifier)前缀过滤数据的ColumnPrefixFilter
        filter = new ColumnPrefixFilter("na".getBytes());
        
        //基于列名(即Qualifier)多个前缀过滤数据的MultipleColumnPrefixFilter
        byte[][] prefixes = new byte[][] {Bytes.toBytes("na"), Bytes.toBytes("me")};
        filter = new MultipleColumnPrefixFilter(prefixes);
 
        //为查询设置过滤条件
        scan.setFilter(filter);
        
        
		scan.addFamily(Bytes.toBytes("base_info"));
		ResultScanner scanner = table.getScanner(scan);
		for(Result r : scanner){
			/**
			for(KeyValue kv : r.list()){
				String family = new String(kv.getFamily());
				System.out.println(family);
				String qualifier = new String(kv.getQualifier());
				System.out.println(qualifier);
				System.out.println(new String(kv.getValue()));
			}
			*/
			//直接从result中取到某个特定的value
			byte[] value = r.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("name"));
			System.out.println(new String(value));
		}
		table.close();
	}

}

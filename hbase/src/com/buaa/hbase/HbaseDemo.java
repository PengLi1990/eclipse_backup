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
	 * �½���
	 */
	@Test
	public void create() throws Exception{
		//Provides an interface to manage HBase database table metadata + general administrative functions. Use HBaseAdmin to create, drop, list, enable and disable tables. Use it also to add and drop table column families.
		//See HTable to add, update, and delete data from an individual table.
		//Currently HBaseAdmin instances are not expected to be long-lived. For example, an HBaseAdmin instance will not ride over a Master restart.
		//����hbase�Ŀͻ���
		HBaseAdmin client = new HBaseAdmin(conf);
		//����
		TableName tableName = TableName.valueOf("production");
		//������
		HTableDescriptor htd = new HTableDescriptor(tableName);
		//��������
		HColumnDescriptor base_info = new HColumnDescriptor("base_info");
		base_info.setMaxVersions(5);
		HColumnDescriptor external_info = new HColumnDescriptor("external_info");
		external_info.setMaxVersions(5);
		
		//�������
		htd.addFamily(base_info);
		htd.addFamily(external_info);
		//����
		client.createTable(htd);
		client.close();
	}
	
	/*
	 * ɾ����ṹ
	 */
	@Test
	public void drop() throws Exception{
		HBaseAdmin client = new HBaseAdmin(conf);
		client.disableTable("production");
		client.deleteTable("production");
		client.close();
	}
	
	/*
	 * ������������
	 */
	@Test
	public void insert() throws Exception{
		//1.���ַ����̲߳���ȫ
		//�õ���
		HTable table = new HTable(conf, "production");
		//ָ��һ��
		Put put = new Put(Bytes.toBytes("p_computer_0001"));
		//����ֵ
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
	 * ɾ����������
	 * ����ɾ���°汾�����ݺ󣬾ɰ汾�ľͶ������ˣ�զ���죿����������
	 */
	@Test
	public void delete() throws Exception{
		HTable table = new HTable(conf,"production");
		//����ɾ��
		Delete del = new Delete(Bytes.toBytes("p_computer_0001"));
		del.deleteColumn(Bytes.toBytes("base_info"), Bytes.toBytes("name"));
		table.delete(del);
		table.close();
	}
	
	/*
	 * ��������
	 */
	@Test
	public void get() throws IOException{
		HTable table = new HTable(conf,"production");
		Get get = new Get(Bytes.toBytes("p_computer_0001"));
		get.setMaxVersions(1);//�趨Ҫ�鼸���汾������
		Result result = table.get(get);
		List<Cell> cells = result.listCells();
		
		for(KeyValue kv : result.list()){
			String family = new String(kv.getFamily());//��ȡ����
			System.out.println(family);
			String qualifier = new String(kv.getQualifier());
			System.out.println(qualifier);
			System.out.println(new String(kv.getValue()));
		}
		table.close();
	}
	
	/*
	 * ���˲�������
	 */
	@Test
	public void scan() throws IOException{
		HTable table = new HTable(conf,"production");
		Scan scan = new Scan(Bytes.toBytes("p_computer_0001"), Bytes.toBytes("p_computer_0003"));
		
		//ǰ׺������----����м�
		Filter filter = new PrefixFilter(Bytes.toBytes("p"));
		
		//�й�����
		ByteArrayComparable rowComparator = new BinaryComparator(Bytes.toBytes("p_computer_0001"));
		RowFilter rf = new RowFilter(CompareOp.LESS_OR_EQUAL, rowComparator);
		
		/**
         * ����rowkey��ʽΪ����������_��������_ID_TITLE
         * Ŀ�꣺����  ��������  Ϊ  2014-12-21  ������
         */
        rf = new RowFilter(CompareOp.EQUAL , new SubstringComparator("_2014-12-21_"));
		
		
		//��ֵ������ 1 ����ƥ���ֽ�����
		new SingleColumnValueFilter("base_info".getBytes(), "name".getBytes(), CompareOp.EQUAL, "zhangsan".getBytes());
		//��ֵ������2 ƥ��������ʽ
		ByteArrayComparable comparator = new RegexStringComparator("zhang.");
		new SingleColumnValueFilter("info".getBytes(), "NAME".getBytes(), CompareOp.EQUAL, comparator);

		//��ֵ������2 ƥ���Ƿ�����Ӵ�,��Сд������
		comparator = new SubstringComparator("wu");
		new SingleColumnValueFilter("info".getBytes(), "NAME".getBytes(), CompareOp.EQUAL, comparator);

		//��ֵ��Ԫ���ݹ���-----family����----�ֽ���������ƥ��
        FamilyFilter ff = new FamilyFilter(
                CompareOp.EQUAL , 
                new BinaryComparator(Bytes.toBytes("base_info"))   //���в�����inf���壬���˽��Ϊ��
                );
        //��ֵ��Ԫ���ݹ���-----family����----�ֽ�����ǰ׺ƥ��
        ff = new FamilyFilter(
                CompareOp.EQUAL , 
                new BinaryPrefixComparator(Bytes.toBytes("inf"))   //���д�����inf��ͷ������info�����˽��Ϊ������������
                );
		
        
       //��ֵ��Ԫ���ݹ���-----qualifier����----�ֽ���������ƥ��
        
        filter = new QualifierFilter(
                CompareOp.EQUAL , 
                new BinaryComparator(Bytes.toBytes("na"))   //���в�����na�У����˽��Ϊ��
                );
        filter = new QualifierFilter(
                CompareOp.EQUAL , 
                new BinaryPrefixComparator(Bytes.toBytes("na"))   //���д�����na��ͷ����name�����˽��Ϊ�����еĸ�������
        		);
		
        //��������(��Qualifier)ǰ׺�������ݵ�ColumnPrefixFilter
        filter = new ColumnPrefixFilter("na".getBytes());
        
        //��������(��Qualifier)���ǰ׺�������ݵ�MultipleColumnPrefixFilter
        byte[][] prefixes = new byte[][] {Bytes.toBytes("na"), Bytes.toBytes("me")};
        filter = new MultipleColumnPrefixFilter(prefixes);
 
        //Ϊ��ѯ���ù�������
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
			//ֱ�Ӵ�result��ȡ��ĳ���ض���value
			byte[] value = r.getValue(Bytes.toBytes("base_info"), Bytes.toBytes("name"));
			System.out.println(new String(value));
		}
		table.close();
	}

}

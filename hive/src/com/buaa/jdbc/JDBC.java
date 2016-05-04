package com.buaa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";//hive��������
	private static String url = "jdbc:hive2://lp3:10000/default";//����hive2��������ӵ�ַ��Hive0.11.0���ϰ汾�ṩ��һ��ȫ�µķ���HiveServer2
	private static String user = "hadoop";//��HDFS�в���Ȩ�޵��û�
	private static String password = "";//�ڷǰ�ȫģʽ�£�ָ��һ���û����в�ѯ����������
	private static String sql = "";
	private static ResultSet res;
	
	public static void main(String[] args) {
	    try {
	        Class.forName(driverName);//����HiveServer2��������
	        Connection conn = DriverManager.getConnection(url, user, password);//����URL����ָ�������ݿ�
	        Statement stmt = conn.createStatement();
	        
	        //�����ı���
	        String tableName = "testHiveDriverTable";
	        
	        /** ��һ��:����ھ���ɾ�� **/
	        sql = "drop table " + tableName;
	        stmt.execute(sql);
	        
	        /** �ڶ���:�����ھʹ��� **/
	        sql = "create table " + tableName + " (key int, value string)  row format delimited fields terminated by '\t' STORED AS TEXTFILE";
	        stmt.execute(sql);
	        
	        // ִ�С�show tables������
	        sql = "show tables '" + tableName + "'";
	        res = stmt.executeQuery(sql);
	        if (res.next()) {
	            System.out.println(res.getString(1));
	        }
	        
	        // ִ�С�describe table������
	        sql = "describe " + tableName;
	        res = stmt.executeQuery(sql);
	        while (res.next()) {  
	            System.out.println(res.getString(1) + "\t" + res.getString(2));
	        }
	        
	        // ִ�С�load data into table������
	        String filepath = "/home/hadoop/djt.txt";//hive�������ڽڵ�ı����ļ�·��
	        sql = "load data local inpath '" + filepath + "' into table " + tableName;
	        stmt.execute(sql);
	        
	        // ִ�С�select * query������
	        sql = "select * from " + tableName;
	        res = stmt.executeQuery(sql);
	        while (res.next()) {
	            System.out.println(res.getInt(1) + "\t" + res.getString(2));
	        }
	        
	        // ִ�С�regular hive query���������˲�ѯ��ת��ΪMapReduce����������
	        sql = "select count(*) from " + tableName;
	        res = stmt.executeQuery(sql);
	        while (res.next()) {
	            System.out.println(res.getString(1));
	        }        
	        conn.close();
	        conn = null;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        System.exit(1);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.exit(1);
	    }
	}

}

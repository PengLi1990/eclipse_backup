package com.buaa.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc����
 * @author ����
 * @time 2016��4��29������4:42:00
 */
public class JdbcClient {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName(driverName);
			//��һ��������jdbc:hive://lp5:10000/default  ����hive2��������ӵ�ַ
			//�ڶ���������hadoop  ��HDFS�в���Ȩ�޵��û�
			//������������hive  �û�����  �ڷǰ�ȫģʽ�£�ָ��һ���û����в�ѯ����������
			Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.66.63:10000/default", "root", "");
			System.out.print(conn.getClientInfo());
			Statement stat = conn.createStatement();
			String sql = "show tables";
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getString(1));//�±���1��ʼ
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

}
package com.buaa.hive1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcClient {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws SQLException {

		try {
			Class.forName(driverName);
			//��һ��������jdbc:hive://lp5:10000/default  ����hive2��������ӵ�ַ
			//�ڶ���������hadoop  ��HDFS�в���Ȩ�޵��û�
			//������������hive  �û�����  �ڷǰ�ȫģʽ�£�ָ��һ���û����в�ѯ����������
			Connection conn = DriverManager.getConnection("jdbc:hive://192.168.66.63:10000/default", "root", "");
			System.out.print(conn.getClientInfo());
			Statement stat = conn.createStatement();
			String sql = "select count(*) from t_order";
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getInt(1));//�±��1��ʼ
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

}

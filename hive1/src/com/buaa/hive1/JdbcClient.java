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
			//第一个参数：jdbc:hive://lp5:10000/default  连接hive2服务的连接地址
			//第二个参数：hadoop  对HDFS有操作权限的用户
			//第三个参数：hive  用户密码  在非安全模式下，指定一个用户运行查询，忽略密码
			Connection conn = DriverManager.getConnection("jdbc:hive://192.168.66.63:10000/default", "root", "");
			System.out.print(conn.getClientInfo());
			Statement stat = conn.createStatement();
			String sql = "select count(*) from t_order";
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getInt(1));//下表从1开始
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

}

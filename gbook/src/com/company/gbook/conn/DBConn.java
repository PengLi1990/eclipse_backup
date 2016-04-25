package com.company.gbook.conn;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConn {
	private static Connection conn;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/usermanager?user=root&password=123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SQLException e){
			
		}
	}
	
	public static Connection getConn(){
		return conn;
	}
	
}

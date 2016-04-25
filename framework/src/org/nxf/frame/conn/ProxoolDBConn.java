package org.nxf.frame.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.nxf.frame.ContextConfig;

public class ProxoolDBConn {
	private static Connection conn = null;
	
	static {
		try {
			conn = DriverManager.getConnection(ContextConfig.alials);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		try {
			if (conn.isClosed()) {
				conn = DriverManager.getConnection(ContextConfig.alials);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
};

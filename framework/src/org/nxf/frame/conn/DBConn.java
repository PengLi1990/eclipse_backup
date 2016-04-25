package org.nxf.frame.conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.nxf.frame.ContextConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DBConn {
	private static String url;
	private static String driver;
	private static String user;
	private static String password;

	private static Connection conn;
	private static Properties prop;

	public DBConn() {

	}
	static {
		if (ContextConfig.connType.equals("xml")) {
			parserXml(ContextConfig.xmlPath);
//			driver = ContextConfig.parserXml(ContextConfig.xmlPath, "driver");
//			url = ContextConfig.parserXml(ContextConfig.xmlPath, "url");
//			user = ContextConfig.parserXml(ContextConfig.xmlPath, "user");
//			password = ContextConfig.parserXml(ContextConfig.xmlPath,"password");
		} else {
			prop = new Properties();
			try {
				prop.load(DBConn.class.getClassLoader().getResourceAsStream(
						"/appconfig.properties"));
				url = prop.getProperty("url");
				user = prop.getProperty("dbuser");
				password = prop.getProperty("dbpass");
				driver = prop.getProperty("driver");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void parserXml(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(fileName);
			NodeList conns = document.getChildNodes();
			for (int i = 0; i < conns.getLength(); i++) {
				Node conn = conns.item(i);
				NodeList connInfo = conn.getChildNodes();
				for (int j = 0; j < connInfo.getLength(); j++) {
					Node node = connInfo.item(j);
					NodeList connMeta = node.getChildNodes();
					for (int k = 0; k < connMeta.getLength(); k++) {
						if (node.getNodeName().equals("url")) {
							url = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("driver")) {
							driver = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("user")) {
							user = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("password")) {
							password = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("ddlauto")) {
							ContextConfig.ddlauto = connMeta.item(k).getTextContent();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnection() {
		try {
			if(conn.isClosed()){
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
};

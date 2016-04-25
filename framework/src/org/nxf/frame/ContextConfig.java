package org.nxf.frame;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContextConfig {
	public static String pagefull = "none";
	public static String packages = "";
	public static String suffix = "";
	public static String redirect = "";
	public static String parameter = "a";
	public static String ddlauto = "none";
	public static String alials = "";
	public static String connType = "xml";
	public static String xmlPath = "";
	public static int pagesize = 0;
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
						if (node.getNodeName().equals("suffix")) {
							suffix = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("parameter")) {
							parameter = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("ddlauto")) {
							ddlauto = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("alials")) {
							alials = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("package")) {
							packages = connMeta.item(k).getTextContent();
						}
						if (node.getNodeName().equals("pagesize")) {
							pagesize = Integer.parseInt(connMeta.item(k).getTextContent());
						}
						if (node.getNodeName().equals("pagefull")) {
							pagefull =connMeta.item(k).getTextContent();
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String parserXml(String fileName,String key) {
		String value = "";
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
						if (node.getNodeName().equals(key)) {
							value = connMeta.item(k).getTextContent();
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
}

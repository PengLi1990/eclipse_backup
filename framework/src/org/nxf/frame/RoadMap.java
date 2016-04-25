package org.nxf.frame;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoadMap extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String classname;

	public RoadMap() {
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		classname = request.getServletPath();

		ContextConfig.redirect = classname.substring(1); // Ìø×ªÊ±Â·¾¶xxx.do

		classname = classname.substring(1, classname.indexOf("."));

		char firstOld = classname.charAt(0);
		char firstNew = (firstOld + "").toUpperCase().charAt(0);
		StringBuffer sb = new StringBuffer();
		sb.append(firstNew);
		sb.append(classname.substring(1));
		sb.append(ContextConfig.suffix);
		classname = sb.toString();

		try {
			HttpServlet c = (HttpServlet) Class.forName(
					ContextConfig.packages + "." + classname).newInstance();
			c.service(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		String xmlFile = config.getInitParameter("xmlFile");
		ContextConfig.xmlPath = this.getClass().getResource("/").toString() + xmlFile;
		ContextConfig.parserXml(ContextConfig.xmlPath);
	}
};

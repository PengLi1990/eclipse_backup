package org.nxf.frame.util;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class UploadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean flag = ServletFileUpload.isMultipartContent(request);
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			if (flag) {
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream fis = iter.next();
					is = fis.openStream();
					if (fis.isFormField()) {
						System.out.print(fis.getFieldName());
						System.out.println(":" + Streams.asString(is));
					} else {
						System.out.println(fis.getName());
						String path = request.getSession().getServletContext()
								.getRealPath("/upload");
						path = path + "/" + fis.getName();
						System.out.println(path);
						fos = new FileOutputStream(path);
						byte[] buff = new byte[1024];
						int len = 0;
						while ((len = is.read(buff)) > 0) {
							fos.write(buff, 0, len);
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void getForm() {

	}

	public String getUploadFileName() {
		return "";
	}

};

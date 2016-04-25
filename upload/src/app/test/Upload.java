package app.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/upload.do")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();


		factory.setSizeThreshold(1024); // yourMaxMemorySize
		factory.setRepository(new File("D:\\apache-tomcat-7.0.65\\temp")); // yourTempDirectory

	
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Process the uploaded items
		Iterator<?> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			// �������������򶼻ᱻ������Ҫ���ж�һ������ͨ�������ļ��ϴ���
			if (item.isFormField()) {
				String name = item.getFieldName();
				if(name.equals("uname")){
					System.out.println(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
				}
		//		String value = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
		//		System.out.println(name + ":" + value);
			} else {
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				int start = fileName.indexOf('.');
				String extName = fileName.substring(start);
				System.out.println(extName);
				String contentType = item.getContentType();
				boolean isInMem = item.isInMemory();
				long sizeInBytes = item.getSize();
				System.out.println(fieldName + ":" + fileName);
				System.out.println("���ͣ�" + contentType);
				System.out.println("�ڴ��Ƿ���ڣ�" + isInMem);
				System.out.println("�ļ���С" + sizeInBytes);

				File uploadedFile = new File("d:\\temp\\" + fileName);
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

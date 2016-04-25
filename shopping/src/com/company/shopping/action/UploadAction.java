package com.company.shopping.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// �����ļ���Ŀ¼
	private static String PATH_FOLDER = "/";
	// �����ʱ�ļ���Ŀ¼
	private static String TEMP_FOLDER = "/";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// ��ʼ��·��
		// �����ļ���Ŀ¼
		PATH_FOLDER = servletCtx.getRealPath("/upload");
		// �����ʱ�ļ���Ŀ¼,���xxx.tmp�ļ���Ŀ¼
		TEMP_FOLDER = servletCtx.getRealPath("/uploadTemp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("upload");
		request.setCharacterEncoding("utf-8"); // ���ñ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// ���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		// ������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem
		 * ��ʽ�� Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(TEMP_FOLDER));
		// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024);

		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			// �ύ��������Ϣ�������list����
			// ����ζ�ſ����ϴ�����ļ�
			// ��������֯����
			List<FileItem> list = upload.parseRequest(request);
			// ��ȡ�ϴ����ļ�
			FileItem item = getUploadFileItem(list);
			// ��ȡ�ļ���
			String filename = getUploadFileName(item);
			// �������ļ���
			String saveName = new Date().getTime() + filename.substring(filename.lastIndexOf("."));
			// �����ͼƬ�����������·��
			String picUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/"+saveName;
			//String picUrl = "/"+request.getContextPath()+"/upload/"+saveName;

			System.out.println("���Ŀ¼:" + PATH_FOLDER);
			System.out.println("�ļ���:" + filename);
			System.out.println("���������·��:" + picUrl);

			// ����д��������
			item.write(new File(PATH_FOLDER, saveName)); // �������ṩ��
			
			PrintWriter writer = response.getWriter();
			
			writer.print("{");
			writer.print("msg:\"�ļ���С:"+item.getSize()+",�ļ���:"+filename+"\"");
			writer.print(",picUrl:\"" + picUrl + "\"");
			writer.print("}");
			writer.close();
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}
	
	private String getUploadFileName(FileItem item) {
		// ��ȡ·����
		String value = item.getName();
		// ���������һ����б��
		int start = value.lastIndexOf("/");
		// ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
		String filename = value.substring(start + 1);
		
		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
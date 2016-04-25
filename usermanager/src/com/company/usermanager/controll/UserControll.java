package com.company.usermanager.controll;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.company.usermanager.bean.User;
import com.company.usermanager.service.UserService;
import com.company.usermanager.tools.VerifyCode;

@WebServlet("/user.do")
public class UserControll extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userService = new UserService();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("a");
		if(a==null){
			return ;
		}else if(a.equals("list")){
			list(request,response);
		}else if(a.equals("save")){
			save(request,response);
		}else if(a.equals("del")){
			delete(request,response);
		}else if(a.equals("checklogin")){
			checklogin(request,response);
		}else if(a.equals("verifycode")){
			VerifyCode.getVerifyCode(request, response);
		}
	}

	private void checklogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String code = (String)request.getSession().getAttribute("VerifyCode");
		String inputCode = request.getParameter("verifycode");
		if(!code.equals(inputCode)){
			request.getSession().setAttribute("errorMsg", "验证码错误!");
			response.sendRedirect("login.jsp");
			return ;
		}
		User user = userService.checkLogin(username,password);
		if(user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("jsp/user/main.jsp");
		}else{
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().println("<script>alert('用户名或密码错误');window.location.href='index.html'</script>");
//		
			request.getSession().setAttribute("errorMsg", "用户名或密码错误!");
			response.sendRedirect("login.jsp");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		userService.delete(id);
		response.sendRedirect("user.do?a=list");
	
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(1024); // yourMaxMemorySize
		//factory.setRepository(new File("D:\\apache-tomcat-7.0.65\\temp")); // yourTempDirectory
	
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
		
		String path = this.getServletContext().getRealPath("/")+"/upload/";
		System.out.println(path);
		User user  = new User();
		
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			// 整个表单的所有域都会被解析，要先判断一下是普通表单域还是文件上传域
			if (item.isFormField()) {
				if(item.getFieldName().equals("username")){
					user.setUsername(item.getString());
				}else if(item.getFieldName().equals("password")){
					user.setPassword(item.getString());
				}else if(item.getFieldName().equals("rule")){
					user.setRule(Integer.parseInt(item.getString()));
				}else if(item.getFieldName().equals("dept")){
					user.setDeptno(Integer.parseInt(item.getString()));
				}
				
			} else {
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				
				int start = fileName.indexOf('.');
				String extName = fileName.substring(start);
				
				System.out.println(extName);
				Date d = new Date();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = sdf.format(d)+extName;
				System.out.println(newFileName);
				
//				String contentType = item.getContentType();
//				boolean isInMem = item.isInMemory();
//				long sizeInBytes = item.getSize();
//				System.out.println(fieldName + ":" + fileName);
//				System.out.println("类型：" + contentType);
//				System.out.println("内存是否存在：" + isInMem);
//				System.out.println("文件大小" + sizeInBytes);
				
				
				File uploadedFile = new File(path + newFileName);
				user.setPic("http://localhost:8080/usermanager/upload/"+newFileName);
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			
		}
		
		userService.save(user);
		response.sendRedirect("jsp/user/main.jsp");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userService.findAll();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("jsp/user/list.jsp").forward(request, response);
	}
}

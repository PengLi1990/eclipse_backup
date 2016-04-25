package com.company.gbook.controll;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.gbook.bean.Gbook;
import com.company.gbook.service.GbookService;

public class GbookControll extends HttpServlet {
	GbookService gbookDao = new GbookService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a= request.getParameter("a");
		if(a==null){
			return ;
		}else if(a.equals("list")){
			list(request,response);
		}else if(a.equals("save")){
			save(request,response);
		}else if(a.equals("del")){
			delete(request,response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("here");
		gbookDao.delete(request.getParameter("id"));
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("here");
		Gbook gbook = new Gbook();
		gbook.setUsername(request.getParameter("username"));
		gbook.setTitle(request.getParameter("title"));
		gbook.setContent(request.getParameter("content"));
		System.out.println(gbook);
		gbookDao.save(gbook);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		List<Gbook> list = gbookDao.findAll();
		for(Gbook g:list){
			out.println("<tr>");
			out.println("<td>"+g.getUsername()+"</td>");
			out.println("<td>"+g.getTitle()+"</td>");
			out.println("<td>"+g.getContent()+"</td>");
			out.println("<td><a href=\"javascript:del('gbook.do?a=del&id="+g.getId()+"')\">del</a></td>");
			out.println("</tr>");
			
		}
	}
}

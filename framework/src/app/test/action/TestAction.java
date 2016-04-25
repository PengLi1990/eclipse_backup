package app.test.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nxf.frame.action.Action;

import app.test.bean.Book;
import app.test.service.BookService;

public class TestAction extends Action{
	
	public TestAction() {
		// TODO Auto-generated constructor stub
		this.setDir("jsp/book");
		this.setService(new BookService("book","app.test.bean.Book"));
	}
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("TestAction's index()");
	}
	
	public void fun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().println("TestAction's fun()");
	}
	
	@Override
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		
		book.setBookname(request.getParameter("bookname"));
		book.setPrice(Float.parseFloat(request.getParameter("price")));
		
		service.save(book);
		
		super.save(request, response);
	}
	
};

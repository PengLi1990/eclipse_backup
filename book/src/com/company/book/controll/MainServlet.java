package com.company.book.controll;

import java.io.IOException;
import java.io.PrintWriter;

import com.company.book.entity.Book;
import com.company.book.service.BookService;

@SuppressWarnings("all")
public class MainServlet extends HttpServlet{
	
	BookService bookService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		bookService = new BookService();
	}

	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		String a = request.getParameter("a");
		
		if(a==null){
			return ;
		}else if(a.equals("list"))
		{
			list(request,response);
		}else if(a.equals("save")){
			System.out.println("here");
			save(request,response);
		}	
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setId(bookService.list().size()+1);
		book.setBookname(request.getParameter("bookname"));
		book.setAuthor(request.getParameter("author"));
		book.setPrice(Float.parseFloat(request.getParameter("price")));
		bookService.save(book);
		//out.println("<script>window.location.href='index.html'</script>");
		response.sendRedirect("index.html");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<table width=400 border=1>");
		out.println("<tr><th>���</th><th>ͼ�����</th><th>����</th><th>����</th></tr>");
		for(Book b:bookService.list())
		{
			out.println("<tr>");
			out.println("<td>"+b.getId()+"</td>");
			out.println("<td>"+b.getBookname()+"</td>");
			out.println("<td>"+b.getAuthor()+"</td>");
			out.println("<td>"+b.getPrice()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");

	}
}
package org.nxf.frame.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nxf.frame.ContextConfig;
import org.nxf.frame.service.IService;
import org.nxf.frame.util.Pages;
import org.nxf.frame.util.VerifyCode;

public class Action extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected IService service;

	protected String dir;
	protected String servlet;
	protected String act;

	public Action() {
		dir = null;
		service = null;
		servlet = ContextConfig.redirect;
	}

	public Action(IService service, String dir) {
		this.service = service;
		this.dir = dir;
		this.servlet = ContextConfig.redirect;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setService(IService service) {
		this.service = service;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		act = request.getParameter(ContextConfig.parameter);
		if (act == null || act.equals("")) {
			index(request, response);
			return;
		} else {
			try {
				Method method = this.getClass().getMethod(act,
						HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void saveupdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(servlet + "?a=list");
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("o", service.getOne(id));

		request.getRequestDispatcher(dir + "/update.jsp").forward(request,
				response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (service != null) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
		}
		response.sendRedirect(servlet + "?a=list");
	}

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.sendRedirect(servlet + "?a=list");
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (service != null) {
			List<?> list = service.getAll();
			request.setAttribute("list", list);
		}
		if (dir != null)
			request.getRequestDispatcher(dir + "/list.jsp").forward(request,
					response);
	}

	/**
	 * datebase page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strp = request.getParameter("p");
		int p = 1;
		if(strp!=null){
			p = Integer.parseInt(strp);
		}
		Pages pages =Pages.getInstance();
		if (service != null) {
			
			pages.setCurrentPage(p);
			pages.setTotalRow(service.getRecordTotal());
			pages.setUrl(ContextConfig.redirect+"?"+ContextConfig.parameter+"=page");
			
			List<?> list = service.getPage((p-1)*pages.getPageSize());
			
			String strPage = "";
			if(ContextConfig.pagefull.equals("full")){
				strPage = pages.getLoopPage();
			}else{
				strPage = pages.getPageStr();
			}
			request.setAttribute("list", list);
			request.setAttribute("pageText",strPage);
		}
		if (dir != null){
			request.getRequestDispatcher(dir + "/list.jsp").forward(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void verifycode(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		VerifyCode.getVerifyCode(request, response);
	}
};

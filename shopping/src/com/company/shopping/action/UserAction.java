package com.company.shopping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.company.shopping.bean.User;
import com.company.shopping.service.UserService;
import com.company.shopping.service.impl.UserServiceImpl;
import com.company.shopping.util.VerifyCode;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private UserService userService;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String list() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String checklogin() throws Exception {
		// TODO Auto-generated method stub
		User user = userService.checklogin(username, password);
		HttpSession session;
		if (user != null) {
			session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", user);
			return SUCCESS;
		} else
			return ERROR;
	}
	
	public String logout() {
		ServletActionContext.getRequest().removeAttribute("user");
		return SUCCESS;
	}
	
	public String verifycode(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		VerifyCode.getVerifyCode(request , response );
		
		return null;
	}

}

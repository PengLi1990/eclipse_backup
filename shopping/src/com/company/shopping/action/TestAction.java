package com.company.shopping.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

public class TestAction {
	private String username;

	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String execute() throws Exception{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		out.println("Hello,World");
		return null;
	}
}

package org.nxf.frame.util;

import org.nxf.frame.ContextConfig;

public class Pages {
	private int totalRow = 0;
	private int size = 10; 				//一页显示的记录数
	private int pageSize = 0; 			//总页数
	private int currentPage = 0;		//当前页
	private String headStr;
	private String prevStr;
	private String nextStr;
	private String tailStr;
	private String url;
	private static Pages instance;
	private static String nextPage = "下一页";

	private Pages() {
		init();
	}
	
	public static Pages getInstance(){
		if(instance==null){
			instance = new Pages();
		}
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPageSize() {
		return totalRow;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalRow = totalPageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		init();
		headStr = "<span>共"+totalRow + "条记录&nbsp;" + pageSize + "页</span>&nbsp;<span>当前第" +currentPage+ "页</span>&nbsp;";
		headStr += "<span><a href='" + url + "&p=1'>首&nbsp;页</a></span>";

		tailStr = "&nbsp;<span><a href='" + url + "&p=" + (pageSize) + "'>尾&nbsp;页</a></span>";
		if (currentPage > 1) {
			prevStr = "&nbsp;<span><a href='" + url + "&p=" + (currentPage - 1) + "'>上一页</a></span>&nbsp;";
		} else {
			prevStr = "&nbsp;<span>上一页</span>&nbsp;";
		}
		if (currentPage < pageSize ) {
			nextStr = "<span><a href='" + url + "&p=" + (currentPage + 1) + "'>"+nextPage+"</a></span>";
		} else {
			nextStr = "&nbsp;<span>"+nextPage+"</span>";
		}
		String str = headStr + prevStr + nextStr + tailStr;
		return str;
	}
	
	private void init() {
		this.size = ContextConfig.pagesize;
		pageSize = totalRow / size;
		if (totalRow % size > 0) {
			pageSize++;
		}
	}

	public String getLoopPage(){
		init();
		headStr = "<span>共"+totalRow + "条记录&nbsp;" + pageSize + "页</span>&nbsp;<span>当前第" +currentPage+ "页</span>&nbsp;";
		headStr += "<span><a href='" + url + "&p=1'>首&nbsp;页</a></span>";

		tailStr = "&nbsp;<span><a href='" + url + "&p=" + (pageSize) + "'>尾&nbsp;页</a></span>";
		if (currentPage > 1) {
			prevStr = "&nbsp;<span><a href='" + url + "&p=" + (currentPage - 1) + "'>上一页</a></span>&nbsp;";
		} else {
			prevStr = "&nbsp;<span>上一页</span>&nbsp;";
		}
		
		String loop = "";
		for (int i = 1; i <= pageSize; i++) {
			loop+= "<span><a href='" + url + "&p=" + i + "'>"+i+"</a></span>&nbsp;";
		}
		
		if (currentPage < pageSize ) {
			nextStr = "<span><a href='" + url + "&p=" + (currentPage + 1) + "'>下一页</a></span>";
		} else {
			nextStr = "<span>下一页</span>";
		}
		String str = headStr + prevStr + loop + nextStr + tailStr;
		return str;
	}

	public int getPageSize() {
		return size;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
};

package com.company.shopping.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.company.shopping.bean.GType;
import com.company.shopping.service.GTService;
import com.opensymphony.xwork2.ActionSupport;

public class GtypeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private GTService gtService;
	private int tid = 0;
	
	
//	private Map<String,Object> dataMap;
//
//	public Map<String, Object> getDataMap() {
//		return dataMap;
//	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public void setGtService(GTService gtService) {
		this.gtService = gtService;
	}

	@Override
	public String execute() {
		System.out.println("here"+tid);
		String str="";
		String json;
		List<?> list = gtService.getTypeByPid(tid);
	
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			for(int i=0;i<list.size();i++){
				GType gt = (GType) list.get(i);
				str += "{'id':'"+gt.getId()+"','typename':'"+gt.getTypename()+"'},";
			}
			
			str+=str.substring(0, str.length()-1);
			json="{options:["+str+"]}";
			//json = "{options:[{'id':'1','typename':'dianzi'},{'id':'2','typename':'shenghuo'}]}";
			System.out.println(json);
			out.print(json);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
//		for(Object o:list){
//			GType gt = (GType) o;
//			dataMap.put("id",gt.getId());
//			dataMap.put("typename", gt.getTypename());
//		}
		return null;
	}
}

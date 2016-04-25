package com.company.gbook.service;

import java.sql.SQLException;
import java.util.List;

import com.company.gbook.bean.Gbook;
import com.company.gbook.dao.GbookDAO;


public class GbookService {
	private GbookDAO gbookDao;
	
	public GbookService() {
		// TODO Auto-generated constructor stub
		gbookDao = new GbookDAO();
	}
	
	public void save(Gbook user){
		try {
			gbookDao.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Gbook> findAll(){
		List<Gbook> list = null;
		try {
			list = gbookDao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list ;
	}

//	public Gbook checklogin(String username, String password) {
//		String condition= "username='"+username+"' and password='"+password+"'";
//		List<Gbook> list = null;
//		try {
//			list = userDao.select(condition);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list!=null ? (list.size()>0 ? list.get(0) : null) :null;
//	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		try {
			gbookDao.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

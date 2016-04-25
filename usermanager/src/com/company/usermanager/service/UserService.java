package com.company.usermanager.service;

import java.sql.SQLException;
import java.util.List;

import com.company.usermanager.bean.User;
import com.company.usermanager.dao.UserDAO;

public class UserService {
	private UserDAO userDao;
	
	public UserService() {
		userDao = new UserDAO();
	}
	
	public void save(User user){
		try {
			userDao.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> findAll(){
		List<User> list = null;
		try {
			list = userDao.select();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list ;
	}

	public User checkLogin(String username, String password) {
		String condition = "username='"+username+"' and password='"+password+"'";
		List<User> list = null;
		// TODO Auto-generated method stub
		try {
			list = userDao.select(condition);
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list!=null ? (list.size()>0?list.get(0):null) : null;
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		try {
			userDao.delete(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

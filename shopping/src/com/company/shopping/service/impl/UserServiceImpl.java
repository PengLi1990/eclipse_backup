package com.company.shopping.service.impl;

import java.util.List;

import com.company.shopping.bean.User;
import com.company.shopping.dao.UserDAO;
import com.company.shopping.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	public void modify(User user) {
		// TODO Auto-generated method stub

	}

	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User checklogin(String username,String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		List<?> list = userDao.findByProperty(user);
		return list.size()>0?(User)list.get(0):null;
	}

}

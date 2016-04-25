package com.company.shopping.service;

import java.util.List;

import com.company.shopping.bean.User;

public interface UserService {
	void save(User user);
	void modify(User user);
	User find(int id);
	List<?> findAll(); 
	User checklogin(String username,String password);
}

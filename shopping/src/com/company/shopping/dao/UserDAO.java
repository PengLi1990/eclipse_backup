package com.company.shopping.dao;

import java.util.List;

import com.company.shopping.bean.User;

public interface UserDAO {
	void insert(User user);
	void update(User user);
	void delete(long id);
	List<?> findAll();
	List<?> findByProperty(User user);
}

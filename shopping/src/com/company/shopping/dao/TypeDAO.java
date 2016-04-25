package com.company.shopping.dao;

import java.util.List;

import com.company.shopping.bean.GType;

public interface TypeDAO {
	List<?> select();

	void insert(GType type);

	List<?> findByProperty(String string, Object o);
}

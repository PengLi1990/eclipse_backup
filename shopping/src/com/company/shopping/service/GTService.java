package com.company.shopping.service;

import java.util.List;

import com.company.shopping.bean.GType;

public interface GTService {
	List<?> getAll();

	void save(GType type);

	List<?> findByPid(int i);

	List<?> getTypeByPid(int tid);
}

package com.company.shopping.service.impl;

import java.util.List;

import com.company.shopping.bean.GType;
import com.company.shopping.dao.TypeDAO;
import com.company.shopping.service.GTService;

public class GTServiceImpl implements GTService {
	
	private TypeDAO typeDao;

	
	public void setTypeDao(TypeDAO typeDao) {
		this.typeDao = typeDao;
	}




	public List<?> getAll() {
		// TODO Auto-generated method stub
		return typeDao.select();
	}




	public void save(GType type) {
		// TODO Auto-generated method stub
		typeDao.insert(type);
	}




	public List<?> findByPid(int id) {
		// TODO Auto-generated method stub
		return typeDao.findByProperty("pid",new Integer(id));
	}




	public List<?> getTypeByPid(int tid) {
		// TODO Auto-generated method stub
		return typeDao.findByProperty("pid",new Integer(tid));
	}

}

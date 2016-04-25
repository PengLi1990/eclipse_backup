package org.nxf.frame.service;

import java.sql.SQLException;
import java.util.List;

import org.nxf.frame.ContextConfig;
import org.nxf.frame.dao.DAO;

public abstract class AbstractService implements IService {
	protected DAO dao;
	protected static AbstractService instance = null;
	
	public AbstractService(String table_name, String bean_name) {
		dao = new DAO(table_name, bean_name);
	}
	
	public AbstractService(DAO dao){
		this.dao = dao;
	}
	
	public static AbstractService newInstance(String table_name, String bean_name){
		if(instance!=null){
			return instance;
		}
		return null;
	}
	
	public void setDao(DAO dao){
		this.dao = dao;
	}
	
	/**
	 * save·½·¨
	 */
	public void save(Object o) {
		try {
			dao.insert(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  getAll()
	 */

	public List<?> getAll() {
		List<?> list = null;
		try {
			list = dao.select("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public List<?> select(String condition) {
		List<?> list = null;
		try {
			list = dao.select(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public void update(Object o) {
		try {
			dao.update(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public int delete(Integer id) {
		int res = 0;
		try {
			res = dao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public List<?> getById(Long id) {
		String str = " id=" + id;
		List<Object> list = null;
		try {
			list = dao.select(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public Object getOne(Integer id) {
		Object u = null;
		String str = " id=" + id;
		List<Object> list = null;
		try {
			list = dao.select(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			u = (Object) list.get(0);
		}
		return u;
	}

	
	public Object getOneByName(String name) {
		Object u = null;
		String str = " username='"+name+"'";
		List<Object> list = null;
		try {
			list = dao.select(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			u = (Object) list.get(0);
		}
		return u;
	}


	public Object getByLimit(Integer p,Integer size) {
		String str="1 limit "+p+","+size;
		try {
			return dao.select(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public int getRecordTotal(){
		try {
			return dao.getRowTotal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<?> getPage(int p) {

		List<?> list = null;
		try {
			list = dao.select(" 1 limit "+p+","+ContextConfig.pagesize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
};

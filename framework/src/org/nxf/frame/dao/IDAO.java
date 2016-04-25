package org.nxf.frame.dao;

import java.util.List;

public interface IDAO {

	/**
	 * 执行insert SQL 语句 添加数据到指定的表
	 * 
	 * @param object
	 *            类型 所有类型的父类
	 * @throws Exception
	 */
	public abstract void insert(Object object) throws Exception;

	/**
	 * 查询数据库表
	 * 
	 * @param str
	 *            添加select查询条件
	 * @return List 类型
	 * @throws Exception
	 */
	public abstract List<Object> select(String str) throws Exception;

	public abstract int delete(Integer id) throws Exception;

	public abstract int update(Object o) throws Exception;
	
}
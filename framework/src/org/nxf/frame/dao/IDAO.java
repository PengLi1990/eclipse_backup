package org.nxf.frame.dao;

import java.util.List;

public interface IDAO {

	/**
	 * ִ��insert SQL ��� ������ݵ�ָ���ı�
	 * 
	 * @param object
	 *            ���� �������͵ĸ���
	 * @throws Exception
	 */
	public abstract void insert(Object object) throws Exception;

	/**
	 * ��ѯ���ݿ��
	 * 
	 * @param str
	 *            ���select��ѯ����
	 * @return List ����
	 * @throws Exception
	 */
	public abstract List<Object> select(String str) throws Exception;

	public abstract int delete(Integer id) throws Exception;

	public abstract int update(Object o) throws Exception;
	
}
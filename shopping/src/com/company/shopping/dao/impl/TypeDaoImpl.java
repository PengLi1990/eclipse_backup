package com.company.shopping.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.shopping.bean.GType;
import com.company.shopping.dao.TypeDAO;

public class TypeDaoImpl extends HibernateDaoSupport implements TypeDAO {

	public List<?> select() {
		// TODO Auto-generated method stub
		//return getHibernateTemplate().find("from GType g where g.pid=0");
		return getHibernateTemplate().find("from GType ");
	}

	public void insert(GType type) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(type);
	}

	public List<?> findByProperty(String propertyName, Object value) {
		String queryString = "from GType as model where model."	+ propertyName + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}
}

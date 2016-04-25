package com.company.shopping.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.shopping.bean.ShopCar;
import com.company.shopping.dao.CarDAO;

public class CarDAOImpl extends HibernateDaoSupport implements CarDAO {
	
	public void insert(ShopCar sc) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(sc);
	}

	public List<?> findByProperty(String field, int uid) {
		String hql = "from ShopCar as sc where sc."+field+"=?";
		return getHibernateTemplate().find(hql,uid);
	}

}

package com.company.shopping.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.shopping.bean.Goods;
import com.company.shopping.dao.GoodsDAO;

public class GoodsDAOImpl extends HibernateDaoSupport implements GoodsDAO {

	public List<?> findAll() {		
		List<?> list = this.getHibernateTemplate().find("from Goods");
		
		return list;
	}

	public void save(Goods goods) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(goods);
	}

	public List<?> findByProperty(String propertyName, Object value) {
		String queryString = "from Goods as model where model."	+ propertyName + "= ? order by model.id desc";
		return getHibernateTemplate().find(queryString, value);
	}
	
	public int getTotalPage(int pageSize){
		int rowTotal = getHibernateTemplate().find("from Goods").size();
		int total = rowTotal / pageSize;
		if(rowTotal%pageSize>0){
			total++;
		}
		
		return total ;
	}
	
	public List<?> getPage(int currentPage){
//		HibernateTemplate template = this.getHibernateTemplate();
//		template.setMaxResults(10);
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Goods");
		query.setMaxResults(10);
		query.setFirstResult((currentPage-1)*10);
		return query.list();
	}

}

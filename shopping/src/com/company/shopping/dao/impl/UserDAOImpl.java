package com.company.shopping.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.company.shopping.bean.User;
import com.company.shopping.dao.UserDAO;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	
	public void insert(User user) {
		// TODO Auto-generated method stub
		this.getSession().save(user);
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(user);
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.delete(session.get(User.class, id));
	}

	public List<?> findAll() {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query = session.createQuery("from User");
		return query.list();
	}

	public List<?> findByProperty(User user) {
		if(user.getId()>0){
			return this.findById(user.getId());
		}else if(user.getUsername()!=null ||!user.getUsername().equals("") ){
			return this.findByUserAndPass(user.getUsername(), user.getPassword());
		}
		return null;
	}
	
	private List<?> findByUserAndPass(String user,String pass){
		Session session = getSession();
		Query query = session.createQuery("from User where username=:name and password=:pass");
		query.setParameter("name", user);
		query.setParameter("pass", pass);
		return query.list();
	}
	
	private List<?> findById(int id){
		Session session = getSession();
		Query query = session.createQuery("from User where id=:id");
		query.setParameter("id", id);
		return query.list();
	}

}

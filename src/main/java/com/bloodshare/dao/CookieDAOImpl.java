package com.bloodshare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.Cookie;

@Repository
public class CookieDAOImpl implements CookieDAO
{

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Cookie read(String id) {
		Session session = sessionFactory.getCurrentSession();
		Cookie cookie=(Cookie) session.get(Cookie.class, id);
		return cookie;
	}

	@Override
	public Cookie update(Cookie t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cookie save(Cookie cookie) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cookie);
		return cookie;
	}

	@Override
	public boolean delete(Cookie cookie) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cookie);
		return true;
	}

}

package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.Cookie;
import com.bloodshare.entity.Donor;

@Repository
public class CookieDAOImpl implements CookieDAO
{
	Logger logger=LoggerFactory.getLogger(CookieDAOImpl.class);
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
		logger.debug("***** Saving Cookies");
		session.saveOrUpdate(cookie);
		return cookie;
	}

	@Override
	public boolean delete(Cookie cookie) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cookie);
		return true;
	}

	@Override
	public List<Cookie> read(Donor donor) {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Cookie.class);
		logger.debug("** in cookei dao "+donor.getId());
		cr.add(Restrictions.eq("donor", donor));
		logger.debug("** restrict added  ");
		List<Cookie> list = cr.list();
		logger.debug("** Returnig .......... ");
		return list;
	}

}

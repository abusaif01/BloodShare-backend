package com.bloodshare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bloodshare.entity.BloodSeekEvent;

public class BloodSeekEventDAOImpl implements BloodSeekEventDAO
{

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public BloodSeekEvent read(String id) {
		return null;
	}

	@Override
	public BloodSeekEvent update(BloodSeekEvent t) {
		return null;
	}

	@Override
	public BloodSeekEvent save(BloodSeekEvent t) {
		Session session = sessionFactory.getCurrentSession();
		String id=(String) session.save(t);
		return session.get(BloodSeekEvent.class, id);
	}

	@Override
	public boolean delete(BloodSeekEvent t) {
		return false;
	}

}

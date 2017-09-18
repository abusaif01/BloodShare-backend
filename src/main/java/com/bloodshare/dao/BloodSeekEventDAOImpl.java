package com.bloodshare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.BloodSeekEvent;

@Repository
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
		int id=(int) session.save(t);
		return session.get(BloodSeekEvent.class, id);
	}

	@Override
	public boolean delete(BloodSeekEvent t) {
		return false;
	}

}

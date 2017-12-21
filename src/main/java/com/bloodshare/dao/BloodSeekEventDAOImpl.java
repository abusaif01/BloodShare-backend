package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

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
		Session session = sessionFactory.getCurrentSession();
		List result = sessionFactory.getCurrentSession()
				.createQuery("from com.bloodshare.entity.BloodSeekEvent a where a.id = :id ")
				.setParameter("id", id)
				.list();
		System.out.println(result);
		return session.get(BloodSeekEvent.class, id);
//		return null;
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

	@Override
	public List<BloodSeekEvent> getEventBySeeker(Donor donor) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(BloodSeekEvent.class);
		criteria.add(Restrictions.eq("userInNeed", donor));
		return criteria.list();
	}

	@Override
	public List<BloodSeekEvent> getEventByDonor(Donor donor) {
		
		return null;
	}

}

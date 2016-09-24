package com.bloodshare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloodshare.entity.Donor;

public class DonorDAOImp implements DonorDAO
{
	private static final Logger logger = LoggerFactory.getLogger(DonorDAOImp.class);
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	@Override
	public Donor read(String donorId) {
		Session session = sessionFactory.openSession();
		Transaction transaction= session.beginTransaction();
		Donor donor=(Donor) session.get(Donor.class, donorId);
		logger.debug("Donor Found "+donor);
		transaction.commit();
		session.close();
		return donor;
	}

	@Override
	public Donor update(Donor t) {
		return null;
	}

	@Override
	public boolean save(Donor donor) {
		Session session = sessionFactory.openSession();
		Transaction transaction= session.beginTransaction();
		session.saveOrUpdate(donor);
		logger.debug("Donor Saved.");
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public boolean delete(Donor t) {
		// TODO Auto-generated method stub
		return false;
	}

}

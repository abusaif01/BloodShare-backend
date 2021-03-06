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

import com.bloodshare.entity.Donor;

@Repository
public class DonorDAOImpl implements DonorDAO
{
	private static final Logger logger = LoggerFactory.getLogger(DonorDAOImpl.class);
	
	private SessionFactory sessionFactory;
	public DonorDAOImpl() {
		logger.debug(" DonorDAOImpl initilating");
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	@Override
	public Donor read(String donorId) {
		Session session = sessionFactory.getCurrentSession();
		Donor donor=(Donor) session.get(Donor.class, donorId);
		logger.debug("Donor Found "+donor);
		return donor;
	}

	@Override
	public Donor update(Donor t) {
		return null;
	}

	@Override
	public Donor save(Donor donor) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(donor);
		
		logger.debug("Donor Saved.");
		return (Donor) session.get(Donor.class, donor.getId());
	}

	@Override
	public boolean delete(Donor t) {
		return false;
	}

	@Override
	public List<Donor> readDonorWithMobileNo(String mobileNo) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Donor.class);
		cr.add(Restrictions.eq("mobile", mobileNo));
		List<Donor> list = cr.list();
		return list;
	}

}

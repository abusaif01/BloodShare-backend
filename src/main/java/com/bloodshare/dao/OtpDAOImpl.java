package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bloodshare.entity.Donor;
import com.bloodshare.entity.Otp;

public class OtpDAOImpl implements OtpDAO
{

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Otp read(String id) {
		throw new UnsupportedOperationException("Update not supported for OTP");
	}

	@Override
	public Otp update(Otp t) {
		throw new UnsupportedOperationException("Update not supported for OTP");
	}

	@Override
	public boolean save(Otp t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
		return true;
	}

	@Override
	public boolean delete(Otp t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
		return true;
	}

	@Override
	public boolean isExist(Otp otp) {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Donor.class);
		cr.add(Restrictions.eq("mobile", otp.getMobile()));
		cr.add(Restrictions.eq("key", otp.getKey()));
		List list = cr.list();
		
		return (list!=null && list.size()==1);
		
	}

}

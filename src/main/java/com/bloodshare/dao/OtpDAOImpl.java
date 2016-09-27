package com.bloodshare.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
		Session session = sessionFactory.getCurrentSession();
		return (Otp) session.get(Otp.class,id);
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

}

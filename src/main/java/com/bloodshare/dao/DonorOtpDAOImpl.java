package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorOtp;

@Repository
public class DonorOtpDAOImpl implements DonotOtpDAO
{

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public DonorOtp read(String id) {
		throw new UnsupportedOperationException("Update not supported for OTP");
	}

	@Override
	public DonorOtp update(DonorOtp t) {
		throw new UnsupportedOperationException("Update not supported for OTP");
	}

	@Override
	public boolean save(DonorOtp t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
		return true;
	}

	@Override
	public boolean delete(DonorOtp t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
		return true;
	}

	@Override
	public boolean isExist(DonorOtp otp) {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(DonorOtp.class);
		cr.add(Restrictions.eq("mobile", otp.getMobile()));
		cr.add(Restrictions.eq("key", otp.getKey()));
		List list = cr.list();
		
		return (list!=null && list.size()==1);
		
	}

}

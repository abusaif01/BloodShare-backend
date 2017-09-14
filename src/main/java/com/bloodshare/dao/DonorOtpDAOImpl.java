package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.DonorOtp;

@Repository
public class DonorOtpDAOImpl implements DonotOtpDAO
{

	private static final Logger logger = LoggerFactory.getLogger(DonorOtpDAOImpl.class);
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
	public DonorOtp update(DonorOtp donorOtp) {
		throw new UnsupportedOperationException("Update not supported for OTP");
	}

	@Override
	public DonorOtp save(DonorOtp donorOtp) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(donorOtp);
		List donorOtplist=session.createCriteria(DonorOtp.class).add(Restrictions.eq("mobile", donorOtp.getMobile())).list();
		if (donorOtplist!=null && donorOtplist.size()==1) {
			return (DonorOtp) donorOtplist.get(0);
		}
		return null;
	}

	@Override
	public boolean delete(DonorOtp donorOtp) {
		Session session = sessionFactory.getCurrentSession();
		try{
			donorOtp=(DonorOtp) session.merge(donorOtp);
			session.delete(donorOtp);
		}catch(NonUniqueObjectException e)
		{
			logger.debug(e.getMessage());
			logger.debug("Merging with seesion");
			donorOtp=(DonorOtp) session.merge(donorOtp);
			session.delete(donorOtp);
		}
		return true;
	}

	@Override
	public boolean isExist(DonorOtp donorOtp) {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(DonorOtp.class);
		cr.add(Restrictions.eq("mobile", donorOtp.getMobile()));
		cr.add(Restrictions.eq("key", donorOtp.getKey()));
		List list = cr.list();
		
		return (list!=null && list.size()==1);
		
	}

}

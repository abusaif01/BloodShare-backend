package com.bloodshare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloodshare.entity.Hospital;

@Repository
public class HospitalDAOImpl implements HospitalDAO
{
	private static final Logger logger = LoggerFactory.getLogger(HospitalDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Hospital read(String id) {
		return null;
	}

	@Override
	public Hospital update(Hospital t) {
		return null;
	}

	@Override
	public Hospital save(Hospital t) {
		return null;
	}

	@Override
	public boolean delete(Hospital t) {
		return false;
	}

	@Override
	public List<Hospital> getHospitalWithNameMatch(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Hospital.class);
		cr.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		return cr.list();
	}

}

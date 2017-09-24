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

import com.bloodshare.entity.DonorLocation;

@Repository
public class DonorLocationDAOImpl implements DonorLocationDAO
{
	private static final Logger logger = LoggerFactory.getLogger(DonorLocation.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public DonorLocation read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DonorLocation update(DonorLocation t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DonorLocation save(DonorLocation t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(DonorLocation t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DonorLocation> searchEntryWithBoundingCoordinate(double[] coordinates) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(DonorLocation.class);
		cr.add(Restrictions.between("latitute", coordinates[0], coordinates[2]));
		cr.add(Restrictions.between("longitute", coordinates[1], coordinates[3]));
		List locationList=cr.list();
		logger.debug("List "+locationList.size());
		return locationList;
	}

}

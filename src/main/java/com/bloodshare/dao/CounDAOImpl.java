package com.bloodshare.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CounDAOImpl implements CountDAO
{

Logger logger=LoggerFactory.getLogger(CookieDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int getCount(Class className)
	{
		int count = ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from "+className.getName()).uniqueResult()).intValue();
		return count;
	}

	@Override
	public int getCount(Class className, String whereCondition) {
		return 0;
	}
}

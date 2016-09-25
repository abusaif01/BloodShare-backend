package com.bloodshare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.util.HibernateUtil;

@Service
public class DonorServiceImpl implements DonorService
{
	private static final Logger logger = LoggerFactory.getLogger(DonorServiceImpl.class);
	public DonorServiceImpl()
	{
		logger.debug(" DonorServiceImpl initilating");
	}
	
	DonorDAO donorDAO;
	
	@Autowired
	public void setDonorDAO(DonorDAO donorDAO) {
		this.donorDAO = donorDAO;
	}

	@Override
	public boolean saveDonor(Donor donor) {
		donorDAO.setSessionFactory(HibernateUtil.getSessionFactory());
		return donorDAO.save(donor);
	}

	@Override
	public Donor getDonor(String id) {
		donorDAO.setSessionFactory(HibernateUtil.getSessionFactory());
		return donorDAO.read(id) ;
	}
	
}

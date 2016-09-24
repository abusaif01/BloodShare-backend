package com.bloodshare.service;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.util.HibernateUtil;

public class DonorServiceImpl implements DonorService
{
	
	DonorDAO donorDAO;
	
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

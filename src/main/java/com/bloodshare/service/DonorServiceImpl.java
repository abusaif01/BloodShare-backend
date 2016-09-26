package com.bloodshare.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Donor;

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

	
	@Transactional
	public boolean saveDonor(Donor donor) {
		return donorDAO.save(donor);
	}

	
	@Transactional
	public Donor getDonor(String id) {
		return donorDAO.read(id) ;
	}

	@Override
	public boolean isUserNew(String mobileNo) {
		List<Donor> list = donorDAO.readDonorWithMobileNo(mobileNo);
		if(list.size()==0)
		return true;
		
		return false;
	}

	@Override
	public boolean loginDonor(String mobileNo) {
		return false;
	}
	
}

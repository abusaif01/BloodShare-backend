package com.bloodshare.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.util.DonorUtils;

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
	public Donor saveDonor(Donor donor) {
		
		List<Donor> tempList=donorDAO.readDonorWithMobileNo(donor.getMobile());
		if(tempList==null || tempList.size()==0)
			return null;
		Donor donorOriginal=donorDAO.readDonorWithMobileNo(donor.getMobile()).get(0);
		DonorUtils.copyAttrib(donor, donorOriginal);
		
		return donorDAO.save(donorOriginal);
	}

	
	@Transactional
	public Donor getDonor(String id) {
		return donorDAO.read(id) ;
	}

	@Transactional
	@Override
	public boolean isUserNew(String mobileNo) {
		if(this.getDonorWithMobileNo(mobileNo)==null )
		return 	false;
		
		return true;
	}

	@Override
	public boolean loginDonor(String mobileNo) {
		return false;
	}


	@Transactional
	@Override
	public Donor getDonorWithMobileNo(String mobile) {
		List<Donor> list = donorDAO.readDonorWithMobileNo(mobile);
		logger.debug(" getDonorWithMobileNo :list size "+list.size());
		if(list.size()==0)
			return null;
		
		return list.get(0);
	}
	
}

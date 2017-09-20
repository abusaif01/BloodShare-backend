package com.bloodshare.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrjun.authentication.CookiesIdGenerator;
import com.adrjun.firebase.FireBaseAdmin;
import com.bloodshare.dao.CookieDAO;
import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Cookie;
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
	CookieDAO cookieDAO;
	FireBaseAdmin fireBase;
	
	@Autowired
	public void setDonorDAO(DonorDAO donorDAO) {
		this.donorDAO = donorDAO;
	}
	
	@Autowired
	public void setFireBase(FireBaseAdmin fireBase)
	{
		this.fireBase=fireBase;
	}
	
	@Autowired
	public void setCookieDAO(CookieDAO cookieDAO) {
		this.cookieDAO = cookieDAO;
	}


	@Transactional
	public Donor saveDonor(Donor donor) {
		List<Donor> tempList=donorDAO.readDonorWithMobileNo(donor.getMobile());
		logger.info("save donor:  list size "+tempList.size());
		if(tempList==null || tempList.size()==0)
			return null;
		Donor donorOriginal=donorDAO.readDonorWithMobileNo(donor.getMobile()).get(0);
		DonorUtils.copyAttrib(donor, donorOriginal);
		
		return donorDAO.save(donorOriginal);
	}

	
	@Transactional
	@Override
	public Donor getDonorWithId(String id) {
		return donorDAO.read(id) ;
	}

	@Transactional
	@Override
	public boolean isUserNew(String mobileNo) {
		if(this.getDonorWithMobileNo(mobileNo)==null )
		return 	true;
		
		return false;
	}

	@Transactional
	@Override
	public String startSession(Donor donor) {
		String cookieId= CookiesIdGenerator.getInstance().generateCookiesId(donor.getId());
		Cookie cookie=new Cookie(cookieId, donor, new Date());
		cookieDAO.save(cookie);
		return cookieId;
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


	@Transactional
	@Override
	public Donor getDonorWithCookie(String cookieId) {
		Cookie cookie=cookieDAO.read(cookieId);
		return cookie.getDonor();
	}
	
}

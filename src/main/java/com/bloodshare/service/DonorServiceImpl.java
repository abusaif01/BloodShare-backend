package com.bloodshare.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrjun.firebase.FireBaseAdmin;
import com.bloodshare.dao.CookieDAO;
import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Cookie;
import com.bloodshare.entity.Donor;
import com.bloodshare.util.DonorStatus;
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

	@Override
	public String authenticateToken(String token) {
		try {
			return this.fireBase.getUid(token);
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
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

//	@Override
	@Transactional
	private Donor getDonorWithFireUid(String fireBaseUid) {
		List<Donor> tempList=donorDAO.readDonorWithFireID(fireBaseUid);
		if(tempList==null|| tempList.size()==0)
			return null;
		return tempList.get(0);
	}

	@Transactional
	@Override
	public boolean startSession(String token,String fireUid) {
		boolean isDonorNew=false;
		
		Donor donor=this.getDonorWithFireUid(fireUid);
		String uid;
		if(donor==null)
		{
			logger.debug("Donor is new");
			donor=new Donor();
			donor.setId(DonorUtils.generateId() );
			donor.setFireId(fireUid);
			try {
				donor.setMobile(this.fireBase.getUserPhoneNumber(fireUid));
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
				logger.error("Could not retrive user Data from Firebase");
			}
			
			donor.setStatus(DonorStatus.UTHENTICATED);
			isDonorNew=true;
			donorDAO.save(donor);
		}
		logger.debug("Donor : "+donor);
		cookieDAO.save(new Cookie(token, donor, new Date()));
		
		return isDonorNew;
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

package com.bloodshare.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
import com.bloodshare.util.DonorStatus;
import com.bloodshare.util.DonorUtils;
import com.bloodshare.util.exeption.DataMalFormException;


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
	@Override
	public Donor updateDonor(Donor donor,Donor donorNew) throws DataMalFormException {
		DonorUtils.validateAndCopyAttrib(donorNew, donor);
		logger.debug("gooing to update Donor "+donor);
		return donorDAO.update(donor);
	}

	@Transactional
	public Donor createDonor(Donor donor) {
		return donorDAO.save(donor);
	}
	
	@Transactional
	@Override
	public Donor getDonorWithId(String id) {
		return donorDAO.read(id) ;
	}

	@Override
	@Transactional
	public Donor getDonorWithFireUid(String fireBaseUid) {
		List<Donor> tempList=donorDAO.readDonorWithFireID(fireBaseUid);
		if(tempList==null|| tempList.size()==0)
			return null;
		return tempList.get(0);
	}

	@Transactional
	@Override
	public String startSession(String fireUid,Donor donor) {
		
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
			donorDAO.save(donor);
		}
		logger.debug("******Donor : "+donor);
		
		List<Cookie> cookies = cookieDAO.read(donor);
		logger.debug("cookied read done"+cookies.size());
		
		if(cookies!=null && cookies.size()==1)
			return cookies.get(0).getCookieId();
		
		if(cookies!=null && cookies.size()>1)
				new RuntimeException("Data Malform multiple Cookies");
		
		String cookieId= CookiesIdGenerator.getInstance().generateCookiesId(donor.getId());
		logger.debug("Saving cookie");
		cookieDAO.save(new Cookie(cookieId, donor, new Date()));
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
		logger.debug("Reading donor with cookie");
		Cookie cookie=cookieDAO.read(cookieId);
		if(cookie==null)
			return null;
		return cookie.getDonor();
	}
}

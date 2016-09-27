package com.bloodshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.OtpDAO;
import com.bloodshare.entity.Otp;
import com.bloodshare.util.OtpUtils;

@Service
public class SMSServiceImpl implements SMSService
{

	OtpDAO otpDAO;
	
	@Autowired
	public void setOtpDAO(OtpDAO otpDAO) {
		this.otpDAO = otpDAO;
	}

	
	@Override
	@Transactional
	public boolean sendOtpSMS(String mobileNo) {
		Otp opt=new Otp(mobileNo, OtpUtils.generateOtp());
		otpDAO.save(opt);
		return true;
	}

	@Override
	public boolean authenticateOtp(Otp otp) {
		if(otpDAO.isExist(otp))
		{
			otpDAO.delete(otp);
			
			return true;
		}
		return false;
	}

}

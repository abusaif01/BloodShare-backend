package com.bloodshare.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.dao.DonotOtpDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorOtp;
import com.bloodshare.util.DonorStatus;
import com.bloodshare.util.DonorUtils;
import com.modules.authentication.CookiesIdGenerator;
import com.modules.otp.OtpGenerator;
import com.modules.sms.service.SMSService;

@Service
public class DonorOtpServiceImpl implements DonorOtpService
{
	private static final Logger logger = LoggerFactory.getLogger(DonorOtpServiceImpl.class);
	DonotOtpDAO otpDAO;
	SMSService smsService;
	DonorDAO donorDAO;
	
	@Autowired
	public void setOtpDAO(DonotOtpDAO otpDAO) {
		this.otpDAO = otpDAO;
	}
	
	@Autowired
	public void setSmsService(SMSService smsService) {
		this.smsService = smsService;
	}
	@Autowired
	public void setDonorDAO(DonorDAO donorDAO) {
		this.donorDAO = donorDAO;
	}

	@Override
	@Transactional
	public boolean sendOtp(String mobileNo) throws Exception {
//		DonorOtp donorOpt=new DonorOtp(mobileNo, OtpGenerator.getInstance().generateOTP(mobileNo));
		DonorOtp donorOpt=new DonorOtp(mobileNo, "1234");
		otpDAO.save(donorOpt);
		smsService.sendSMS(mobileNo, donorOpt.getKey());
		return true;
	}

	@Override
	@Transactional
	public Donor autheticateOtp(DonorOtp donorOtp) throws Exception {
		if(otpDAO.isExist(donorOtp))
		{
			logger.info("opt data found");
			otpDAO.delete(donorOtp);
			Donor donor;
			List<Donor> donorList=donorDAO.readDonorWithMobileNo(donorOtp.getMobile());
			if(donorList.size()==0)
			{
				logger.debug("Donor is new");
				donor=new Donor();
				donor.setMobile(donorOtp.getMobile());
				donor.setId(DonorUtils.generateId() );
				donor.setStatus(DonorStatus.UTHENTICATED);
			}else if(donorList.size()==1)
			{
				logger.debug("Donor is OLD");
				donor=donorList.get(0);
				//applicable for old user.if donor already have an status then keep that.
				if(donor.getStatus()==DonorStatus.UN_UTHENTICATED)
				{
					donor.setStatus(DonorStatus.UTHENTICATED);
				}
			}else throw new Exception("0/1 user Not Found");
			
			donorDAO.save(donor);
			return donor;
//			return CookiesIdGenerator.getInstance().generateCookiesId(donor.getId());
//			return true;
		}
		logger.info("opt data NOT found");
		return null;
	}

}

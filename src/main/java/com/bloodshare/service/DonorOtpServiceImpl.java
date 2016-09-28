package com.bloodshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.dao.DonotOtpDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorOtp;
import com.bloodshare.util.DonorStatus;
import com.modules.otp.OtpGenerator;
import com.modules.sms.service.SMSService;

@Service
public class DonorOtpServiceImpl implements DonorOtpService
{

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
		DonorOtp donorOpt=new DonorOtp(mobileNo, OtpGenerator.getInstance().generateOTP(mobileNo));
		otpDAO.save(donorOpt);
		smsService.sendSMS(mobileNo, donorOpt.getKey());
		return true;
	}

	@Override
	@Transactional
	public boolean autheticateOtp(DonorOtp donorOtp) throws Exception {
		if(otpDAO.isExist(donorOtp))
		{
			otpDAO.delete(donorOtp);
			Donor donor;
			List<Donor> donorList=donorDAO.readDonorWithMobileNo(donorOtp.getMobile());
			if(donorList.size()==0)
			{
				donor=new Donor();
				donor.setMobile(donorOtp.getMobile());
				// set donor key
				donor.setId("");
				donor.setStatus(DonorStatus.UTHENTICATED);
			}else if(donorList.size()==1)
			{
				donor=donorList.get(0);
				//applicable for old user.if donor already have an status then keep that.
				if(donor.getStatus()==DonorStatus.UTHENTICATED)
				{
					donor.setStatus(DonorStatus.UTHENTICATED);
				}
			}else throw new Exception("Problem in autheticatio");
			
			donorDAO.save(donor);
			return true;
		}
		return false;
	}

}

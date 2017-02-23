package com.bloodshare.service;

import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorOtp;

public interface DonorOtpService {

	public boolean sendOtp(String mobileNo) throws Exception;
	public Donor autheticateOtp(DonorOtp donorOtp) throws Exception;
}

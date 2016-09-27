package com.bloodshare.service;

import com.bloodshare.entity.Otp;

public interface SMSService {
	public boolean sendOtpSMS(String mobileNo);
	public boolean authenticateOtp(Otp otp);
}

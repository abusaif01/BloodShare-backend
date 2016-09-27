package com.bloodshare.service;

import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService
{

	@Override
	public boolean sendOtpSMS(String mobileNo) {
		return false;
	}

	@Override
	public boolean authenticateOtp(String mobileNo, String key) {
		return false;
	}

}

package com.bloodshare.service;

public interface SMSService {
	public boolean sendOtpSMS(String mobileNo);
	public boolean authenticateOtp(String mobileNo,String key);
}

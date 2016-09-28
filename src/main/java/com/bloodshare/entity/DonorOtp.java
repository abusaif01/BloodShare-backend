package com.bloodshare.entity;

import javax.persistence.Entity;

@Entity(name="donor_otp")
public class DonorOtp {
	
	private String mobile;
	private String key;
	
	public DonorOtp() {
	}
	
	public DonorOtp(String mobile, String key) {
		this.mobile = mobile;
		this.key = key;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}

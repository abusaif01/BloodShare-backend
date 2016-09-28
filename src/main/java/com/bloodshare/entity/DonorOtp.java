package com.bloodshare.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="donor_otp")
public class DonorOtp {
	@Id
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

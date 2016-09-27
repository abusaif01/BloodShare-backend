package com.bloodshare.entity;

import javax.persistence.Entity;

@Entity
public class Otp {
	
	private String mobileNo;
	private String key;
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}

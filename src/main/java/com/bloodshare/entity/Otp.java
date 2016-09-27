package com.bloodshare.entity;

import javax.persistence.Entity;

@Entity
public class Otp {
	
	private String mobile;
	private String key;
	
	public Otp() {
	}
	
	public Otp(String mobile, String key) {
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

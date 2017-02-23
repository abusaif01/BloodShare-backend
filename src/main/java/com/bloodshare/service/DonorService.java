package com.bloodshare.service;

import com.bloodshare.entity.Donor;

public interface DonorService {

	public Donor saveDonor(Donor donor);
	public Donor getDonorWithCookie(String cookieId);
	public Donor getDonorWithId(String id);
	public Donor getDonorWithMobileNo(String mobile);
	public boolean isUserNew(String mobileNo);
	public String startSession(Donor donor);
	
}

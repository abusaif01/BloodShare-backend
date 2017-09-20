package com.bloodshare.service;

import com.bloodshare.entity.Donor;

public interface DonorService {

	public String authenticateToken(String token);
	public Donor saveDonor(Donor donor);
	public Donor getDonorWithCookie(String cookieId);
	public Donor getDonorWithId(String id);
	public Donor getDonorWithMobileNo(String mobile);
//	public Donor getDonorWithFireUid(String fireBaseUid);
	public boolean startSession(String token,String fireUid);
	
}

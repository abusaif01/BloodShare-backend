package com.bloodshare.service;

import com.bloodshare.entity.Donor;
import com.bloodshare.util.exeption.DataMalFormException;

public interface DonorService {

	public String authenticateToken(String token);
	public Donor updateDonor(Donor donor,Donor donorNew) throws DataMalFormException;
	public Donor getDonorWithCookie(String cookieId);
	public Donor getDonorWithId(String id);
	public Donor getDonorWithMobileNo(String mobile);
	public Donor getDonorWithFireUid(String fireBaseUid);
	public String startSession(String fireUid,Donor donor);
	
	public boolean deleteDonor(Donor donor);
}

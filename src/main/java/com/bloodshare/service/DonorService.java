package com.bloodshare.service;

import com.bloodshare.entity.Donor;

public interface DonorService {

	public boolean saveDonor(Donor donor);
	public Donor getDonor(String id);
}

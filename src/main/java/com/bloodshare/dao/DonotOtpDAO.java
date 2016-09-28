package com.bloodshare.dao;

import com.bloodshare.entity.DonorOtp;

public interface DonotOtpDAO extends GenericDAO<DonorOtp>
{
	public boolean isExist(DonorOtp otp);
}

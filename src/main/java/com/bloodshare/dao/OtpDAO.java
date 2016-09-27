package com.bloodshare.dao;

import com.bloodshare.entity.Otp;

public interface OtpDAO extends GenericDAO<Otp>
{
	public boolean isExist(Otp otp);
}

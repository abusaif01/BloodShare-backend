package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.Cookie;
import com.bloodshare.entity.Donor;

public interface CookieDAO extends GenericDAO<Cookie,String>
{
	public List<Cookie> read(Donor donor);
}

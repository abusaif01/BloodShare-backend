package com.bloodshare.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bloodshare.entity.Donor;

public interface DonorDAO extends GenericDAO<Donor,String>
{
	public List<Donor> readDonorWithMobileNo(String mobileNo); 
	public List<Donor> readDonorWithFireID(String fireId); 
}

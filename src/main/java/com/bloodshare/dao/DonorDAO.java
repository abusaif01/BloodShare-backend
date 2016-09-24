package com.bloodshare.dao;

import org.hibernate.SessionFactory;

import com.bloodshare.entity.Donor;

public interface DonorDAO extends GenericDAO<Donor>
{
	public void setSessionFactory(SessionFactory sf);
}

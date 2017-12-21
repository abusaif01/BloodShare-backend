package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.Hospital;

public interface HospitalDAO extends GenericDAO<Hospital, String>
{
	public List<Hospital> getHospitalWithNameMatch(String name);
}

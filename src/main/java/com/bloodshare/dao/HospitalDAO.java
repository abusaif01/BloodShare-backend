package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.Hospital;

public interface HospitalDAO extends GenericDAO<Hospital, Integer>
{
	public List<Hospital> getHospitalWithNameMatch(String name);
}

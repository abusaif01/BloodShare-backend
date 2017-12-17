package com.bloodshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.HospitalDAO;
import com.bloodshare.entity.Hospital;

@Service
public class HospitalServiceImpl implements HospitalService
{

	@Autowired
	HospitalDAO hospitalDao;
	
	@Transactional
	@Override
	public List<Hospital> searchHospitalsWithName(String query) {
		return hospitalDao.getHospitalWithNameMatch(query);
	}

	@Override
	public Hospital saveHospital(Hospital hospital) {
		return null;
	}

}

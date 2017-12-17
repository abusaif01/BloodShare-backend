package com.bloodshare.service;

import java.util.List;

import com.bloodshare.entity.Hospital;

public interface HospitalService {
	List<Hospital> searchHospitalsWithName(String query);
	Hospital saveHospital(Hospital hospital);
}

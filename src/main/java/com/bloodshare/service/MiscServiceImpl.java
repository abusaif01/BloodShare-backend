package com.bloodshare.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.CountDAO;
import com.bloodshare.entity.Donor;

@Service
public class MiscServiceImpl implements MiscService
{

	@Autowired
	CountDAO countDao;
	
	@Override
	@Transactional
	public Map<String, Integer> getSatisticsData() {
		Map<String,Integer> stat=new HashMap<String,Integer>();
//		stat.put("donor", countDao.getCount(Donor.class));
//		stat.put("event", countDao.get );
//		stat.put("event-old", );
//		stat.put("collected-blood", );
		
		return stat;
	}

}

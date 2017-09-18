package com.bloodshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodshare.dao.BloodSeekEventDAO;
import com.bloodshare.entity.BloodSeekEvent;

@Service
public class BloodSeekEventSeviceImpl implements BloodSeekEventSevice
{

	BloodSeekEventDAO eventDao;
	
	@Autowired
	public void setEventDao(BloodSeekEventDAO eventDao) {
		this.eventDao = eventDao;
	}


	@Override
	public BloodSeekEvent createNewEvent(BloodSeekEvent event) {
		return eventDao.save(event);
	}

}

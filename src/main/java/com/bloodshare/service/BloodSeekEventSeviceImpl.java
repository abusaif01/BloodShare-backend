package com.bloodshare.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloodshare.dao.BloodSeekEventDAO;
import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

@Service
public class BloodSeekEventSeviceImpl implements BloodSeekEventSevice
{

	private static final Logger logger = LoggerFactory.getLogger(BloodSeekEventSeviceImpl.class);

	BloodSeekEventDAO eventDao;
	
	@Autowired
	public void setEventDao(BloodSeekEventDAO eventDao) {
		this.eventDao = eventDao;
	}


	@Override
	@Transactional
	public BloodSeekEvent createNewEvent(BloodSeekEvent event) {
		event= eventDao.save(event);
		logger.debug("Event saved "+event);
		List<Donor> searchedDonorList=this.searchDonorBasedOnLocation(event.getLocation(), this.calculateUserToFind(event));
		
		this.sentNotification(searchedDonorList);
		
		
		return event;
		
	}
	
	private int calculateUserToFind(BloodSeekEvent event)
	{
		return 0;
	}
	
	private List<Donor> searchDonorBasedOnLocation(String location,int searchLimit)
	{
		return null;
	}
	
	private boolean sentNotification(List<Donor> donorList)
	{
		return true;
	}
	private void scheduleNextCheck()
	{
		
	}
}

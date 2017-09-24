package com.bloodshare.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrjun.search.LocationBasedSearch;
import com.bloodshare.dao.BloodSeekEventDAO;
import com.bloodshare.dao.DonorLocationDAO;
import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorLocation;
import com.bloodshare.entity.Location;

@Service
public class BloodSeekEventSeviceImpl implements BloodSeekEventSevice
{

	private static final Logger logger = LoggerFactory.getLogger(BloodSeekEventSeviceImpl.class);

	@Autowired
	BloodSeekEventDAO eventDao;
	@Autowired
	DonorLocationDAO donorLocationDao;
	


	@Override
	@Transactional
	public BloodSeekEvent createNewEvent(BloodSeekEvent event) {
//		event= eventDao.save(event);
//		logger.debug("Event saved "+event);
		
		List<DonorLocation> searchedDonorList=this.searchDonorBasedOnLocation(event.getLocation(),1,this.calculateUserToFind(event));
		
		this.sentNotification(searchedDonorList);
		
		return event;
		
	}
	
	private int calculateUserToFind(BloodSeekEvent event)
	{
		return (event.getQunatity()-(event.getConfirmed()+event.getWaiting()));
	}
	
	private List<DonorLocation> searchDonorBasedOnLocation(Location location,double distanceParameter,int searchLimit)
	{
		LocationBasedSearch locationSearch=new LocationBasedSearch();
		double[] boundingCoordinates=locationSearch.findBoundingCoordinates(location, distanceParameter);
		List<DonorLocation> searchResult=donorLocationDao.searchEntryWithBoundingCoordinate(boundingCoordinates);
		logger.debug("seacrch Resulot "+searchResult.size());
		for (DonorLocation donorLocation : searchResult) {
			logger.debug("Loacation Found :"+donorLocation.getLocation()+" "+ donorLocation.getLatitute()+" , "+donorLocation.getLongitute());
			logger.debug("Loacation Found :"+donorLocation);
		}
		searchResult= this.filterLocationSearchResult(searchResult, searchLimit);
		return searchResult;
		
	}
	
	private List<DonorLocation>filterLocationSearchResult(List<DonorLocation> result,int limit)
	{
		return result;
	}
	
	private boolean sentNotification(List<DonorLocation> donorList)
	{
		return true;
	}
	private void scheduleNextCheck()
	{
		
	}
}

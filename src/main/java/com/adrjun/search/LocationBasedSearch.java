package com.adrjun.search;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adrjun.search.location.GeoLocation;

public class LocationBasedSearch {
	private static final double SEARCH_PARAMETER_IN_KM=2;
	private static final double  EARTH_RADUIUS=6371;
	private static final Logger logger=LoggerFactory.getLogger(LocationBasedSearch.class);
	public List<String> findNearLocationEntries(double lat,double log)
	{
		this.findNearLocationEntries(lat, log, SEARCH_PARAMETER_IN_KM);
		return null;
	}
	
	public double[] findNearLocationEntries(double lat,double log,double distanceInKm)
	{
		
		GeoLocation loc=GeoLocation.fromDegrees(lat, log);
		GeoLocation[] boundingCordinates = loc.boundingCoordinates(distanceInKm, EARTH_RADUIUS);
		
		double [] points=new double[4];
		points[0]=boundingCordinates[0].getLatitudeInDegrees();
		points[1]=boundingCordinates[0].getLongitudeInDegrees();
		points[2]=boundingCordinates[1].getLatitudeInDegrees();
		points[3]=boundingCordinates[1].getLongitudeInDegrees();
		logger.debug("bounding conrdinates "+ Arrays.toString(boundingCordinates));
		
		return points;
	}
	
	public double findDistance(double latFrom,double logFrom,double lat2,double log2)
	{
		GeoLocation locFrom=GeoLocation.fromDegrees(latFrom, logFrom);
		GeoLocation locTo=GeoLocation.fromDegrees(lat2, log2);
		double distance=locFrom.distanceTo(locTo, EARTH_RADUIUS);
		logger.debug("distance "+ distance);
		return distance;
	}
	
}

package com.adrjun.search;

import org.junit.Test;

import com.bloodshare.entity.Location;

import static org.junit.Assert.*;

import java.util.List;

public class LocationBasedSearchTest {

	@Test
	public void LoactionSearchTest()
	{
		LocationBasedSearch locSerach = new LocationBasedSearch();
		double lat=23.794506;
		double log=90.424096;
		Location loc=new Location();
		loc.setLatitude(lat);
		loc.setLongitude(log);
		double[] points = locSerach.findBoundingCoordinates(loc,100.0);
//		assertEquals(points, locSerach.findNearLocationEntries(lat,log,30));
		assertEquals(30, locSerach.findDistance(lat,log, points[0],points[1] ));
	}
}

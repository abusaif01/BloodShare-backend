package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.DonorLocation;

public interface DonorLocationDAO extends GenericDAO<DonorLocation>
{
	List<DonorLocation> searchEntryWithBoundingCoordinate(double[] coordinates);
}

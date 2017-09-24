package com.adrjun.search.location;

import com.bloodshare.entity.DonorLocation;

public class GoogleLoactionUtil {

	public static DonorLocation convertLoaction(String locationName)
	{
		DonorLocation lo=new DonorLocation();
		lo.setLatitute(0.22222);
		lo.setLongitute(0.36987);
		return lo;
	}
}

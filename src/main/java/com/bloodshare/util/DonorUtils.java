package com.bloodshare.util;

import java.util.UUID;

import com.adrjun.search.location.GoogleLoactionUtil;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorLocation;
import com.bloodshare.util.exeption.DataMalFormException;

public class DonorUtils {

	public static String generateId()
	{
		return UUID.randomUUID().toString();
	}	
	
	public static void validateAndCopyAttrib(Donor from,Donor to) throws DataMalFormException
	{
		if(from.getBirthDate()!=null)
			to.setBirthDate(from.getBirthDate());
		else if(to.getBirthDate()==null) throw new DataMalFormException("Date of Birth can not be empty");
		if(from.getBloodGroup()!=null)
			to.setBloodGroup(from.getBloodGroup());
		else if(to.getBloodGroup()==null) throw new DataMalFormException("Blood Group cannot be empty");
		if(from.getName()!=null)
			to.setName(from.getName());
		if(from.getStatus()!=null)
			to.setStatus(from.getStatus());
		if(from.getLocation()!=null)
		{
			if(to.getLocation()!=null)
				from.getLocation().setId(to.getLocation().getId());
			to.setLocation(from.getLocation());
		}
		else if(from.getLocationInString()!=null)
		{
			DonorLocation loaction=GoogleLoactionUtil.convertLoaction(from.getLocationInString());
			if(to.getLocation()!=null)
				loaction.setId(to.getLocation().getId());
			to.setLocation(loaction);
		}
		else if (to.getLocation()==null) throw new DataMalFormException("Location cannot be empry");
	}
	public static boolean isDonorInfoValid(Donor donor)
	{
		if(donor.getBirthDate()== null || donor.getBloodGroup()==null 
				|| donor.getLocation()==null)
			return false;
		return true;
	}
	
//	public static void main(String[] args) {
//		System.out.println( generateId());
//	}
}

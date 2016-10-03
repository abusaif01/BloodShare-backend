package com.bloodshare.util;

import java.util.UUID;

import com.bloodshare.entity.Donor;

public class DonorUtils {

	public static String generateId()
	{
		return UUID.randomUUID().toString();
	}	
	
	public static void copyAttrib(Donor from,Donor to)
	{
		if(from.getBirthDate()!=null)
			to.setBirthDate(from.getBirthDate());
		if(from.getBloodGroup()!=null)
			to.setBloodGroup(from.getBloodGroup());
		if(from.getName()!=null)
			to.setName(from.getName());
		if(from.getStatus()!=null)
			to.setStatus(from.getStatus());
		
	}
	
//	public static void main(String[] args) {
//		System.out.println( generateId());
//	}
}

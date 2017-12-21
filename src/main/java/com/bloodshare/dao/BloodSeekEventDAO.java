package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

public interface BloodSeekEventDAO extends GenericDAO<BloodSeekEvent,String>
{
	public List<BloodSeekEvent> getEventBySeeker(Donor donor);
	public List<BloodSeekEvent> getEventByDonor(Donor donor);
}

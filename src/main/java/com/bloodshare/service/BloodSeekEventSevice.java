package com.bloodshare.service;

import java.util.List;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

public interface BloodSeekEventSevice {
	BloodSeekEvent createNewEvent(BloodSeekEvent event);
	BloodSeekEvent getEventById(String id);
	List<BloodSeekEvent> getUserSeekedEvent(Donor seeker);
	List<BloodSeekEvent> getUserRespondedEvent(Donor donor);
}

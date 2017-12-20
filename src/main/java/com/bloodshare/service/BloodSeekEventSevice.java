package com.bloodshare.service;

import com.bloodshare.entity.BloodSeekEvent;

public interface BloodSeekEventSevice {
	BloodSeekEvent createNewEvent(BloodSeekEvent event);
	BloodSeekEvent getEventById(String id);
	
}

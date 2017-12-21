<<<<<<< HEAD
package com.bloodshare.service;

import java.util.List;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

public interface BloodSeekEventSevice {
	BloodSeekEvent createNewEvent(BloodSeekEvent event);
	BloodSeekEvent getEventById(int id);
	List<BloodSeekEvent> getUserSeekedEvent(Donor seeker);
	List<BloodSeekEvent> getUserRespondedEvent(Donor donor);
}
=======
package com.bloodshare.service;

import com.bloodshare.entity.BloodSeekEvent;

public interface BloodSeekEventSevice {
	BloodSeekEvent createNewEvent(BloodSeekEvent event);
	BloodSeekEvent getEventById(String id);
	
}
>>>>>>> a367a0e90bdac3780e3f1f3fc9a8bbe6397eeb3d

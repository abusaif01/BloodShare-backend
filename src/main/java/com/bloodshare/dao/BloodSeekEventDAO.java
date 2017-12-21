<<<<<<< HEAD
package com.bloodshare.dao;

import java.util.List;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;

public interface BloodSeekEventDAO extends GenericDAO<BloodSeekEvent,Integer>
{
	public List<BloodSeekEvent> getEventBySeeker(Donor donor);
	public List<BloodSeekEvent> getEventByDonor(Donor donor);
}
=======
package com.bloodshare.dao;

import com.bloodshare.entity.BloodSeekEvent;

public interface BloodSeekEventDAO extends GenericDAO<BloodSeekEvent,String>
{

}
>>>>>>> a367a0e90bdac3780e3f1f3fc9a8bbe6397eeb3d

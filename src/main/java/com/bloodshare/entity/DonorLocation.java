package com.bloodshare.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DonorLocation extends Location
{
	
	@JsonIgnore
	@OneToOne(mappedBy="location", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private Donor donor;

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	@Override
	public String toString() {
		return "DonorLocation ["+super.toString()+", donorId="+((donor==null)?null:donor.getId())+"]";
	}
	
}

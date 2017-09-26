package com.bloodshare.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EventLocation extends Location
{
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_id")
	private BloodSeekEvent event;

	public BloodSeekEvent getEvent() {
		return event;
	}

	public void setEvent(BloodSeekEvent event) {
		this.event = event;
	}
	

	@Override
	public String toString() {
		return "DonorLocation ["+super.toString()+", eventID="+((event==null)?null:event.getId())+"]";
	}
	
	
}

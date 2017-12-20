package com.bloodshare.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DonorForEventPk implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Column(name="donor_id")
	private String donorId;
	
	@Column(name="event_id")
	private String eventId;

	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
}

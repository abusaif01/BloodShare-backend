package com.bloodshare.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bloodshare.util.DonorStatusInEvent;

@Entity
public class DonorForEvent {

	@EmbeddedId
	private DonorForEventPk donorForEventPk;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="donor_id")
	private Donor donor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_id")
	private BloodSeekEvent event;

	@Enumerated(EnumType.ORDINAL)
	private DonorStatusInEvent status;
	
	public DonorForEventPk getDonorForEventPk() {
		return donorForEventPk;
	}

	public void setDonorForEventPk(DonorForEventPk donorForEventPk) {
		this.donorForEventPk = donorForEventPk;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public BloodSeekEvent getEvent() {
		return event;
	}

	public void setEvent(BloodSeekEvent event) {
		this.event = event;
	}

	public DonorStatusInEvent getStatus() {
		return status;
	}

	public void setStatus(DonorStatusInEvent status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DonorForEvent [donorForEventPk=" + donorForEventPk + ", status=" + status + "]";
	}
}

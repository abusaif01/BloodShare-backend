package com.bloodshare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EventLocation extends Location
{
	@Id
	@Column(name="event_id")
	@GeneratedValue(generator="SharedPrimaryKeyGenerator")
	@GenericGenerator(name="SharedPrimaryKeyGenerator",strategy="foreign",parameters =  @Parameter(name="property",value="event"))
	int id;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BloodSeekEvent event;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

package com.bloodshare.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="blood_seek_event")
public class BloodSeekEvent {

	@Id
	@GeneratedValue
	private int  id;
	
	@Column(name="boold_group")
	private String booldGroup;
	
	@Column(name="quantity")
	private int qunatity;
	
	@Column(name="target_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	private Date neededDate;
	
	@Column(name="crated")
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	private Date createdDate; 
	
	@OneToOne(mappedBy="event",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	private EventLocation location;
	
	@Column(name="image")
	private String image;
	
	@Column(name="confimed_donor")
	private int confirmed;
	
	@Column(name="waiting_donor")
	private int waiting;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="donor_in_need")
	private Donor userInNeed;

	@JsonIgnore
	@OneToMany(mappedBy="event",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,orphanRemoval = true)
	List<DonorForEvent> donorForEvent;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBooldGroup() {
		return booldGroup;
	}

	public void setBooldGroup(String booldGroup) {
		this.booldGroup = booldGroup;
	}

	public int getQunatity() {
		return qunatity;
	}

	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}

	public Date getNeededDate() {
		return neededDate;
	}

	public void setNeededDate(Date neededDate) {
		this.neededDate = neededDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public EventLocation getLocation() {
		return location;
	}

	public void setLocation(EventLocation location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public Donor getUserInNeed() {
		return userInNeed;
	}

	public void setUserInNeed(Donor userInNeed) {
		this.userInNeed = userInNeed;
	}
}

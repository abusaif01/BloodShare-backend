package com.bloodshare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="blood_seek_event")
public class BloodSeekEvent {

	@Id
	@GeneratedValue
	private String  id;
	
	@Column(name="quantity")
	private int qunatity;
	
	@Column(name="target_date")
	private Date neededDate;
	
	@Column(name="crated")
	private Date createdDate; 
	
	@Column(name="location")
	private String location;
	
	@Column(name="image")
	private String image;
	
	@Column(name="confimed_donor")
	private int confirmed;
	
	@Column(name="waiting_donor")
	private int waiting;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_Id")
	private Donor userInNeed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
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

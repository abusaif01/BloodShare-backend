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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="blood_seek_event")
public class BloodSeekEvent {

	@Id
	@GeneratedValue(generator="stringKey")
	@GenericGenerator(name="stringKey", strategy = "uuid2")
	private String  id;
	
	@Column(name="blood_group")
	private String bloodGroup;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="target_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	private Date neededDate;
	
	@Column(name="crated")
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	private Date createdDate; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="hospital_id",insertable=false,updatable=false)
	private Hospital hospital;
	
<<<<<<< HEAD
	@Column(name="hospital_id",nullable=false)
=======
	@Column(name="hospital_id")
>>>>>>> a367a0e90bdac3780e3f1f3fc9a8bbe6397eeb3d
	private String hospitalId;
	
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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

	@Override
	public String toString() {
		return "BloodSeekEvent [id=" + id + ", bloodGroup=" + bloodGroup + ", quantity=" + quantity + ", neededDate="
				+ neededDate + ", createdDate=" + createdDate + ", hospital=" + hospital + ", hospitalId=" + hospitalId
				+ ", image=" + image + ", confirmed=" + confirmed + ", waiting=" + waiting + ", userInNeed="
				+ userInNeed.getId() + "]";
	}
	
}

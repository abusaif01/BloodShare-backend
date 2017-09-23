package com.bloodshare.entity;

import javax.persistence.CascadeType;
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
public class DonorLocation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@JsonIgnore
	@OneToOne(mappedBy="location", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private Donor donor;
	
	@Column
	private double latitute;
	@Column
	private double longitute;
	
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Donor getDonor() {
		return donor;
	}




	public void setDonor(Donor donor) {
		this.donor = donor;
	}




	public double getLatitute() {
		return latitute;
	}




	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}




	public double getLongitute() {
		return longitute;
	}




	public void setLongitute(double longitute) {
		this.longitute = longitute;
	}




	@Override
	public String toString() {
		return "DonorLocation [id=" + id + ", latitute=" + latitute + ", longitute=" + longitute + ", donorId="+((donor==null)?null:donor.getId())+"]";
	}
	
	
}

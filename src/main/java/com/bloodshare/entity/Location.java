package com.bloodshare.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class Location {

	@JsonIgnore
	@Id
	@GeneratedValue
	private int id;
	
	@Column 
	private String location;
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
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
		return "Location [id=" + id + ", location=" + location + ", latitute=" + latitute + ", longitute=" + longitute
				+ "]";
	}
	
}

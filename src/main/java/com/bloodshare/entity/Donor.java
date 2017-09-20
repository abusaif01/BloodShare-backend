package com.bloodshare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.bloodshare.util.DonorStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donor {
	
	@JsonIgnore
	@Id
	@Column(name="id")
	private String id;

	@JsonIgnore
	@Column(name="fire_id",unique=true)
	private String fireId;
	
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="blood_group")
	private String bloodGroup;
	
	@Column(name="birthdate")
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	private Date birthDate;

	@Enumerated(EnumType.ORDINAL)
	private DonorStatus status;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	

	public DonorStatus getStatus() {
		return status;
	}

	public void setStatus(DonorStatus status) {
		this.status = status;
	}

	
	public String getFireId() {
		return fireId;
	}

	public void setFireId(String fireId) {
		this.fireId = fireId;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", fireId=" + fireId + ", mobile=" + mobile + ", name=" + name + ", bloodGroup="
				+ bloodGroup + ", birthDate=" + birthDate + ", status=" + status + "]";
	}
}

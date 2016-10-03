package com.bloodshare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.bloodshare.util.DonorStatus;

@Entity
public class Donor {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="blood_group")
	private String bloodGroup;
	
	@Column(name="birthdate")
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

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", mobile=" + mobile + ", bloodGroup=" + bloodGroup
				+ ", birthDate=" + birthDate + ", status=" + status + "]";
	}
}

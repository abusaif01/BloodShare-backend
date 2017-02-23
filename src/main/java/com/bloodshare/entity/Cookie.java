package com.bloodshare.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Cookie {
	
	public Cookie() {
	}
	
	public Cookie(String cookieId, Donor donor, Date assingDate) {
		this.cookieId = cookieId;
		this.donor = donor;
		this.assingDate = assingDate;
	}
	
	@Id
	@Column(name="id")
	private String cookieId;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	private Donor donor;
	
	@Column(name="assingdate")
	private Date assingDate;
	
	public String getCookieId() {
		return cookieId;
	}
	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}
	public Donor getDonor() {
		return donor;
	}
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
	public Date getAssingDate() {
		return assingDate;
	}
	public void setAssingDate(Date assingDate) {
		this.assingDate = assingDate;
	}
}

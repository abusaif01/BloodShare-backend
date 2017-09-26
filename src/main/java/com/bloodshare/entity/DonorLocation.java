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
public class DonorLocation extends Location
{
	@Id
	@Column(name="donor_id",unique=true, nullable=false)
	@GeneratedValue(generator="SharedPrimaryKeyGenerator")
	@GenericGenerator(name="SharedPrimaryKeyGenerator",strategy="foreign",parameters =  @Parameter(name="property",value="donor"))
	private String id;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Donor donor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	@Override
	public String toString() {
		return "DonorLocation ["+super.toString()+", donorId="+((donor==null)?null:donor.getId())+"]";
	}
	
}

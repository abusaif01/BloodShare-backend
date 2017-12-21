package com.bloodshare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Hospital extends Location
{

	@Id
	@GeneratedValue(generator="stringKey")
	@GenericGenerator(name="stringKey", strategy = "uuid2")
	private String  id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}

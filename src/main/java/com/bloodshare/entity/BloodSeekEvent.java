package com.bloodshare.entity;

import java.util.Date;

public class BloodSeekEvent {

	private String  id;
	private String location;
	private String image;
	
	private Date createdDate; 
	private Date neededDate;
	
	private int qunatity;
	private int confirmed;
	private int waiting;
	
	private Donor userInNeed;
	
	
}

package com.bloodshare.controller;

import java.io.IOException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.dao.DatabaseHelper;
import com.bloodshare.entity.Donor;


@RestController
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@PathVariable("id") String donorId)
	{
		
		Session session = DatabaseHelper.getSession();
		session.beginTransaction();
		Donor donor=(Donor) session.get(Donor.class, donorId);
		logger.debug("Donor Found "+donor);
		return new ResponseEntity<Donor>(donor,HttpStatus.OK);
			
		
	}
	
}

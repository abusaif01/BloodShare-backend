package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.Donor;
import com.bloodshare.service.DonorService;


@RestController
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	private DonorService donorService;
	
	@Autowired(required=true)
	@Qualifier(value="donorService")
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@PathVariable("id") String donorId)
	{
		logger.debug(donorService.toString());
		
		return new ResponseEntity<Donor>(donorService.getDonor(donorId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/create", method = RequestMethod.PUT, 
			consumes="application/json")
	public ResponseEntity<Boolean>  getDonor(@RequestBody Donor donor)
	{
		if(donorService.saveDonor(donor))
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}

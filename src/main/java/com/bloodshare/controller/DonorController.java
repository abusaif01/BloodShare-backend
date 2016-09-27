package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.Donor;
import com.bloodshare.service.DonorService;
import com.bloodshare.service.SMSService;


@RestController
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	private DonorService donorService;
	private SMSService sMSService;
	
	@Autowired
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	@Autowired
	public void setSMSService(SMSService sMSService) {
		this.sMSService = sMSService;
	}

	@RequestMapping(value="/user/check_isnew_send_otp/",method= RequestMethod.GET)
	public ResponseEntity<Boolean> checkMobileNumber(@RequestParam(value="mobile") String mobileNo )
	{
		logger.debug("Checking user");
		boolean isNew=donorService.isUserNew(mobileNo);
		boolean isSendSuccessfull=sMSService.sendOtpSMS(mobileNo);
		return new ResponseEntity<Boolean>(isNew,HttpStatus.OK);
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

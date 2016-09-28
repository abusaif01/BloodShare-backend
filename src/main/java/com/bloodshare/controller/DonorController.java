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
import com.bloodshare.service.DonorOtpService;
import com.bloodshare.service.DonorService;


@RestController
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	private DonorService donorService;
	private DonorOtpService donorOtpService;
	
	@Autowired
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	@Autowired
	public void setDonorOtpService(DonorOtpService donorOtpService) {
		this.donorOtpService = donorOtpService;
	}

	@RequestMapping(value="/user/check_isnew_send_otp/",method= RequestMethod.GET)
	public ResponseEntity<Boolean> checkMobileNumber(@RequestParam(value="mobile") String mobileNo )
	{
		logger.debug("Checking user");
		boolean isNew=donorService.isUserNew(mobileNo);
		try {
			boolean isSendSuccessfull=donorOtpService.sendOtp(mobileNo);
			if(isSendSuccessfull)
			return new ResponseEntity<Boolean>(isNew,HttpStatus.OK);
			return new ResponseEntity<Boolean>(isNew,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(isNew,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
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

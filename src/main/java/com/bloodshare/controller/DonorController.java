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
import com.bloodshare.entity.DonorOtp;
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

	@RequestMapping(value="/user/check_isnew_send_otp",method= RequestMethod.GET)
	public ResponseEntity<Boolean> checkMobileNumber(@RequestParam(value="mobile") String mobileNo )
	{
		logger.debug("Checking user if new");
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
	
	@RequestMapping(value="/user/authenticate",consumes="application/json",method=RequestMethod.POST)
	public ResponseEntity<Boolean> authenticateWithOtp(@RequestBody DonorOtp donorOtp)
	{
		logger.debug("going to authenticate");
		try {
			boolean isAuthenticated=donorOtpService.autheticateOtp(donorOtp);
			logger.info("result: isAuthenticated = "+isAuthenticated);
			if(isAuthenticated)
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@RequestMapping(value="/user/get/{id}", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@PathVariable("id") String donorId)
	{
		logger.debug("retriving user with ID");
		
		return new ResponseEntity<Donor>(donorService.getDonor(donorId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/get/mobile/{mobile}", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonorWithMobile(@PathVariable("mobile") String mobile)
	{
		logger.debug("retriving user with MObile NO");
		Donor donor = donorService.getDonorWithMobileNo(mobile);
		logger.debug("donor found "+donor);
		return new ResponseEntity<Donor>(donor,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/updateInfo", method = RequestMethod.POST, 
			consumes="application/json")
	public ResponseEntity<Donor>  updateDonor(@RequestBody Donor donor)
	{
		logger.debug("Saving Donor");
		Donor donorUpdated = donorService.saveDonor(donor);
		if(donorUpdated==null)
		{
			return new ResponseEntity<Donor>(HttpStatus.NOT_FOUND);
		}
		else
		return new ResponseEntity<Donor>(donorUpdated,HttpStatus.OK);
		
//		return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
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
@RequestMapping("/user")
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

	@RequestMapping(value="/check_isnew_send_otp",method= RequestMethod.GET)
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
	
	@RequestMapping(value="/authenticate",consumes="application/json",method=RequestMethod.POST)
	public ResponseEntity<String> authenticateWithOtp(@RequestParam String token)
	{
		logger.debug("going to authenticate");
		String uid=donorService.authenticateToken(token);
		logger.info("donor uid = "+uid);
		if(uid==null)
			return new ResponseEntity<String>("Token Not Authenticated",HttpStatus.UNAUTHORIZED);
		int userType = donorService.isUserNew(token)? 1:2;
		return new ResponseEntity<String>(""+userType,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@CookieValue("SESSION_ID") String sessionId )
	{
		logger.debug("****************************" +sessionId);
		logger.debug("retriving user with Cookie");
		
		return new ResponseEntity<Donor>(donorService.getDonorWithCookie(sessionId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonorWithId(@PathVariable("id") String donorId, @RequestParam(required=false,name="type") String idType)
	{
		Donor donor=null;
		if(idType!=null && ( idType.equalsIgnoreCase("2") || idType.equalsIgnoreCase("m") || idType.equalsIgnoreCase("mobile")))
		{
			logger.debug("retriving user with MObile NO");
			donor = donorService.getDonorWithMobileNo(donorId);
			logger.debug("donor found "+donor);
		}
		else {
			donor=donorService.getDonorWithId(donorId);
			logger.debug("retriving user with ID");
		}
		return new ResponseEntity<Donor>(donor,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes="application/json")
	public ResponseEntity<Donor>  updateDonor(@CookieValue("SESSION_ID") String sessionId,@RequestBody Donor donor)
	{
		logger.debug("Saving Donor :"+donor);
		Donor originalDonor=donorService.getDonorWithCookie(sessionId);
		if(originalDonor==null)
			return new ResponseEntity<Donor>(HttpStatus.NOT_FOUND);
		
		donor.setId(originalDonor.getId());
		Donor donorUpdated = donorService.saveDonor(donor);
		return new ResponseEntity<Donor>(donorUpdated,HttpStatus.OK);
	}
}

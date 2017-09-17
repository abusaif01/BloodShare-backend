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
	public ResponseEntity<String> authenticateWithOtp(@RequestBody DonorOtp donorOtp)
	{
		logger.debug("going to authenticate");
		try {
			Donor donor=donorOtpService.autheticateOtp(donorOtp);
			logger.info("result: authentication donor = "+donor);
			if(donor!=null)
			{
				String cookiesId=donorService.startSession(donor);
				return new ResponseEntity<String>(cookiesId,HttpStatus.OK);
			}
			else return new ResponseEntity<String>("Your OTP did not Match",HttpStatus.UNAUTHORIZED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	@RequestMapping(value="/user", method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@CookieValue("SESSION_ID") String sessionId )
	{
		logger.debug("****************************" +sessionId);
		logger.debug("retriving user with Cookie");
		
		return new ResponseEntity<Donor>(donorService.getDonorWithCookie(sessionId),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET, 
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
	
	
	@RequestMapping(value="/user", method = RequestMethod.POST, 
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

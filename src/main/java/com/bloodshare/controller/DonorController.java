package com.bloodshare.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorLocation;
import com.bloodshare.service.DonorService;
import com.bloodshare.util.DonorUtils;
import com.bloodshare.util.exeption.DataMalFormException;


@RestController
@RequestMapping("/user")
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	private DonorService donorService;
	@Autowired
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	
	@RequestMapping(value="/authenticate",consumes="application/json",produces="application/json", method=RequestMethod.POST)
	public ResponseEntity<Map<String,String>> authenticate(@RequestBody Map<String,String> requestData)
	{
		logger.debug("requested Data "+requestData);
		
		Map<String,String> responseMap=new HashMap<String,String>();
		String fireToken=requestData.get("firebase_token");
		
		logger.debug("going to authenticate, token "+fireToken);
		String fireUid=donorService.authenticateToken(fireToken);
		logger.info("donor uid = "+fireUid);
		if(fireUid==null)
		{
			responseMap.put("response", "Authentication Failed");
			responseMap.put("result", "failed");
			responseMap.put("isSuccess", "false");
			responseMap.put("isAuthenticated", "false");
			logger.debug("\n\nReturnigng FROM HERE ");
			return new ResponseEntity<Map<String,String>>(responseMap,HttpStatus.UNAUTHORIZED);
		}
		Donor donor=donorService.getDonorWithFireUid(fireUid);
		
		boolean isUserNew= (donor==null || !DonorUtils.isDonorInfoValid(donor))?true:false;
		logger.debug("isUserNew "+isUserNew);
		logger.debug("\nGoing to call start session");
		String accessToken=donorService.startSession(fireUid,donor);
		logger.debug("accessToken "+accessToken);

		responseMap.put("user_access_token", accessToken);
		responseMap.put("is_user_new", String.valueOf(isUserNew));
		
		logger.debug("responseMap "+responseMap);

		return new ResponseEntity<Map<String,String>>(responseMap,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, 
			consumes="*",produces = "application/json")
	public ResponseEntity<Donor>  getDonor(@RequestAttribute(name="session_donor",required=true) Donor donor )
	{
		if(donor.getLocation()==null)
			donor.setLocation(new DonorLocation());
		return new ResponseEntity<Donor>(donor,HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{id}", method = RequestMethod.GET, 
//			consumes="*",produces = "application/json")
//	public ResponseEntity<Donor>  getDonorWithId(@PathVariable("id") String donorId, @RequestParam(required=false,name="type") String idType)
//	{
//		Donor donor=null;
//		if(idType!=null && ( idType.equalsIgnoreCase("2") || idType.equalsIgnoreCase("m") || idType.equalsIgnoreCase("mobile")))
//		{
//			logger.debug("retriving user with MObile NO");
//			donor = donorService.getDonorWithMobileNo(donorId);
//			logger.debug("donor found "+donor);
//		}
//		else {
//			donor=donorService.getDonorWithId(donorId);
//			logger.debug("retriving user with ID");
//		}
//		if(donor==null)
//			return new ResponseEntity<Donor>(donor,HttpStatus.NOT_FOUND);
//		return new ResponseEntity<Donor>(donor,HttpStatus.OK);
//	}
	
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes="application/json",produces="application/json")
	public ResponseEntity<?>  updateDonor(@RequestAttribute(name="session_donor",required=true) Donor donor,@RequestBody Donor donorToUpdate)
	{
		logger.debug("New Donor Data:"+donorToUpdate);
		Donor donorUpdated = donorToUpdate;
		try {
			donorUpdated = donorService.updateDonor(donor, donorToUpdate);
		} catch (DataMalFormException e) {
			return new ResponseEntity<Map<String,String>>(Collections.singletonMap("message", e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Donor>(donorUpdated,HttpStatus.OK);
	}
}

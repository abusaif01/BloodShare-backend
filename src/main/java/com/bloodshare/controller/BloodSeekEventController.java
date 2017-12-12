package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;
import com.bloodshare.service.BloodSeekEventSevice;


@RestController
@RequestMapping("/bloodSeekEvent")
public class BloodSeekEventController {

	private static final Logger logger = LoggerFactory.getLogger(BloodSeekEventController.class);
	private BloodSeekEventSevice eventService;
	
	@Autowired
	public void setEventService(BloodSeekEventSevice eventService) {
		this.eventService = eventService;
	}



	@GetMapping(value= "/{id}")
	public ResponseEntity<BloodSeekEvent> getEvent(@RequestAttribute(name="session_donor",required=true) Donor donor,
			@PathVariable(required=true)  String id)
	{
		System.out.println("INSIDE   ");
		logger.debug("Id Found "+id);
		logger.debug("Id Found "+id);
		int idInt=Integer.parseInt(id);
		return new ResponseEntity<BloodSeekEvent>(eventService.getEventById(idInt)  ,HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<BloodSeekEvent> createEvent(@RequestAttribute(name="session_donor",required=true) Donor donor,@RequestBody BloodSeekEvent event)
	{
		logger.debug("****create event request found");
		event.setUserInNeed(donor);
		event=eventService.createNewEvent(event);
		logger.debug("event created : "+event.getId());
		return new ResponseEntity<BloodSeekEvent>(event,HttpStatus.CREATED);
	}
}

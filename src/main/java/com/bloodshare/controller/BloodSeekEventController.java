package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.BloodSeekEvent;
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



	@RequestMapping(value="/",method= RequestMethod.POST)
	public ResponseEntity<BloodSeekEvent> createEvent(@RequestBody BloodSeekEvent event)
	{
		logger.debug("create event request found");
		event=eventService.createNewEvent(event);
		logger.debug("event created : "+event.);
		return new ResponseEntity<BloodSeekEvent>(event,HttpStatus.CREATED);
	}
}

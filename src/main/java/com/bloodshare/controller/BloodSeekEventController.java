package com.bloodshare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bloodshare.entity.BloodSeekEvent;


@RestController
@RequestMapping("/bloodSeekEvent")
public class BloodSeekEventController {

	@RequestMapping(value="/",method= RequestMethod.POST)
	public ResponseEntity<BloodSeekEvent> createEvent(@RequestBody BloodSeekEvent event)
	{
		return null;
		
	}
}

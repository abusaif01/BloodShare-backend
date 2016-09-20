package com.bloodshare.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StoryController {

	private static final Logger logger = LoggerFactory.getLogger(StoryController.class);
	
	@RequestMapping("/sayHello")
    public String greeting() {
        logger.debug("Every Thing is working . Ready to GO.");
		return "Hello";
    }
}

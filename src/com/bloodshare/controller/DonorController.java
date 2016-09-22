package com.bloodshare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonorController {

	private static final Logger logger = LoggerFactory.getLogger(DonorController.class);
	
	@RequestMapping("/sayHello")
    public String greeting() {
        logger.debug("Every Thing is working . Ready to GO.");
		return "Hello";
    }
}

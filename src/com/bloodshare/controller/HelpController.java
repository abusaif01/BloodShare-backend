package com.bloodshare.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class HelpController {
	
	private final RequestMappingHandlerMapping handlerMapping;
	 
	 @Autowired
	 public HelpController(RequestMappingHandlerMapping handlerMapping) {
	  this.handlerMapping = handlerMapping;
	 }
	  
	 @RequestMapping(value="/endpointdoc", method=RequestMethod.GET)
	 public String show(Model model) {
		 
		 Map<RequestMappingInfo, HandlerMethod> handlers = this.handlerMapping.getHandlerMethods();
		 
		 
		 
		 
		 return  this.handlerMapping.getHandlerMethods().toString();
	 }
	 
}

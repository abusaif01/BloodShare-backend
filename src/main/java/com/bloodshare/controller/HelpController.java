package com.bloodshare.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(HelpController.class);
	private final RequestMappingHandlerMapping handlerMapping;
	 
	 @Autowired
	 public HelpController(RequestMappingHandlerMapping handlerMapping) {
	  this.handlerMapping = handlerMapping;
	 }
	 
	 @RequestMapping(value="/help", method=RequestMethod.GET)
	 public String show(Model model) {
		 StringBuilder sb=new StringBuilder();
		 
		 Map<RequestMappingInfo, HandlerMethod> handlers = this.handlerMapping.getHandlerMethods();
		 Set<RequestMappingInfo> keys = handlers.keySet();
		 Iterator<RequestMappingInfo> it = keys.iterator();
		 while (it.hasNext()) {
			RequestMappingInfo requestMappingInfo = (RequestMappingInfo) it.next();
			sb.append(requestMappingInfo.getPatternsCondition());
			sb.append("\t\t----");
			sb.append(requestMappingInfo.getMethodsCondition());
			sb.append("\t\t----");
			sb.append(requestMappingInfo.getHeadersCondition() );
			sb.append("\t\t----");
			sb.append(requestMappingInfo.getConsumesCondition());
			sb.append("\t\t----");
			sb.append(requestMappingInfo.getProducesCondition());
			sb.append("<br/>");
		}
		 
		return  sb.toString();
	 }
	 
	 @RequestMapping("/hello")
    public String greeting() {
        logger.debug("Every Thing is working . Ready to GO.");
		return "Hello From Blood Share ";
    }
}

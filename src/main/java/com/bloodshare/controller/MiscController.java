package com.bloodshare.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.bloodshare.entity.Hospital;
import com.bloodshare.service.DonorService;
import com.bloodshare.service.HospitalService;
import com.bloodshare.service.MiscService;

@RestController
public class MiscController {
	
	private static final Logger logger = LoggerFactory.getLogger(MiscController.class);
	private final RequestMappingHandlerMapping handlerMapping;
	 
	 @Autowired
	 public MiscController(RequestMappingHandlerMapping handlerMapping) {
	  this.handlerMapping = handlerMapping;
	 }
	 
	 @Autowired
	 HospitalService hospitalService;
	 @Autowired
	 MiscService miscService;
	 @Autowired
	 
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
	 
	 @RequestMapping(value="/",produces="application/json")
    public ResponseEntity<Map<String,String>> greeting() {
        logger.debug("Every Thing is working . Ready to GO.");
		return  new ResponseEntity<Map<String,String>>(Collections.singletonMap("hello", "Wellcome to Blood Share") ,HttpStatus.OK);
    }
	 
	@GetMapping(value="/hospital")
	public ResponseEntity<List<Hospital>> getHospialList(@RequestParam("key") String quesrySting)
	{
		logger.debug("hospital searching key "+quesrySting);
		return new ResponseEntity<List<Hospital>>( hospitalService.searchHospitalsWithName(quesrySting),HttpStatus.OK);
	}
	@GetMapping(value="/statistics")
	@ResponseBody
	public Map<String,Integer> getStatistics()
	{
		logger.debug("statistics collecting ");
		
		return miscService.getSatisticsData();
	}
}

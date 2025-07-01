package com.javateam.demoMVC.controller;

// 2)
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

// 1)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	// 1)
	private static final Logger logger
		= LoggerFactory.getLogger(DemoController.class);
	
	// 2)
//	private static final Logger logger
//		= LogManager.getLogger();
	
	@GetMapping("/")
	public String demo() {
		
		logger.info("demo 로그");
		
		return "demo";
	}
	
}

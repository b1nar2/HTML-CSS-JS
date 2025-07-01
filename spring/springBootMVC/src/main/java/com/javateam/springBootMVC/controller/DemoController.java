package com.javateam.springBootMVC.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // Log4J
//import org.slf4j.Logger;//Slf4J
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;



@Controller // <<Controller>> : stereotype(UML)
@Slf4j // Slf4j 로그(log) 객체
//@Log4j2 // Log4J 로그(log) 객체
//@Log // Java 로그(log) 객체

public class DemoController {

	
	// SLF4J
//	private static final Logger logger
//		=LoggerFactory.getLogger(DemoController.class);
//	 => @Slf4j

	// Log4J 로그(log) 객체
//	private static final Logger logger
//		= LogManager.getLogger();

	
	@GetMapping("/")
	// @RequestMapping(method = RequestMethod.GET)
	public String demo(Model model, HttpServletRequest request) {
		
		log.info("데모 Controller");
		
		// 인자 생성 : forward 이동 방식 "전용(only)" 인자
		model.addAttribute("java", 21);
		request.setAttribute("spring", "6.1.21");
		
		return "demo"; // demo.html forward(이동)
	}
	
	// @RequestMapping("/demo")
	@RequestMapping(value="/demo", method=RequestMethod.GET)
	public ModelAndView demo2(HttpServletRequest request) {
		
		log.info("demo2");
		
		ModelAndView modelAndView = new ModelAndView("demo2");
		modelAndView.addObject("HTML", 5);
		// modelAndView.setViewName("demo2")
		request.setAttribute("CSS", 3);
		
		return modelAndView;
	}
	
	
}
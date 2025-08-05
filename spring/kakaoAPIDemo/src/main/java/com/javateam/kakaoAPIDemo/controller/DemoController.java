package com.javateam.kakaoAPIDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {
	
	@GetMapping
	public String home() {
		return "demo";
	}
	
}
package com.javateam.springFileuploadDragDrop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Value("${spring.servlet.multipart.max-file-size}")
	// application.properties 파일의 spring.servlet.multipart.max-file-size=500KB
	private String maxFileSize;

	@GetMapping("/")
	public String home(Model model) {

		log.info("##### fileUpload ######");
		
		log.info("파일 업로드 전송 한계량 : " + maxFileSize);
		
		long maxSize = this.toSize(maxFileSize);  
		
		model.addAttribute("maxFileSize", maxSize);		
		
		return "fileUpload";
		// return "fileUpload2";
	}
	
	private long toSize(String size) {
		
		long result = 0;
		
		if (size.contains("MB")) {
			
			result = Long.parseLong(size.replaceAll("MB", "")) * 1024 * 1024;
			
		} else if (size.contains("KB")) {
			
			result = Long.parseLong(size.replaceAll("KB", "")) * 1024;
			
		} else if (size.contains("GB")) {
			
			result = Long.parseLong(size.replaceAll("GB", "")) * 1024 * 1024 * 1024;
			
		} else { // 기본값 => MB
			
			result = Long.parseLong(size.replaceAll("MB", "")) * 1024 * 1024;
		}
		
		return result;
	}

}

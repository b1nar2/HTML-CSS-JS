package com.javateam.springBootMVC.controller;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.springBootMVC.domain.TestVO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2

public class DemoRestController {
	
	@GetMapping("rest")
	public String rest() throws JsonProcessingException {
		
		log.info("rest");
		
		// JSON
		TestVO testVO = TestVO.builder()
							  .id("abcd1234")
							  .name("Java")
							  .joindate(Date.valueOf("2025-07-01"))
							  .build();
	
		// VO => JSON(application/json) String
		return new ObjectMapper().writeValueAsString(testVO);
				
//		return "demo"; // 글(text/plain) media feed
		
	}	
		@GetMapping("rest2")
		public ResponseEntity<TestVO> rest2() {
			
			log.info("rest2");
			
			// JSON
			TestVO testVO = TestVO.builder()
								  .id("abcd1234")
								  .name("Java")
								  .joindate(Date.valueOf("2025-07-01"))
								  .build();
		
//		return ResponseEntity.ok(testVO); // ok => 200(성공코드)
		return new ResponseEntity<>(testVO, HttpStatus.OK);
		
	}

}

package com.javateam.springBootMVC.controller;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.springBootMVC.domain.TestVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class DemoRestController {

	private static final Logger log = LogManager.getLogger();
	
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
//		return new ObjectMapper().writeValueAsString(testVO);
				
		return "demo"; // 글(text/plain) media feed
		
	}	
	
		
	@GetMapping("rest2")
	public ResponseEntity<TestVO> rest2(HttpServletRequest request,
			@RequestParam("id") String id) {
		
//		log.info("rest2 servlet id: " + request.getParameter(id));
		log.info("rest2 String id : " + id); 
			
		// JSON
		TestVO testVO = TestVO.builder()
							  .id(id)
							  .name("")
							  .joindate(Date.valueOf("2025-07-01"))
							  .build();
		
//		TestVO testVO = null; // 204유발 코드 (확인용)
		
		ResponseEntity<TestVO> resEntity = null; // 리턴
		
		try {
		
			// 성공(200)/ 내용없음(204)
			if (testVO != null) { // 200
				
				resEntity = new ResponseEntity<>(testVO, HttpStatus.OK);
				
				// 417(Expectation failed)
				if(testVO.getName().trim().equals("")==true); {
					throw new Exception("이름 필드가 누락되었습니다.");
				}
				
			} else { // 204				
				new ResponseEntity<>(testVO, HttpStatus.NO_CONTENT);
			}
			
		} catch(Exception e) {
			
//			log.error("", id, e, e, e, request, id, testVO, resEntity, e);
			log.error("DemoRestController.rest2 error : " + e.getMessage());
			log.error("DemoRestController.rest2 error : REST 서비스 에러 발생");
			// e.printStackTace();
			resEntity = new ResponseEntity<>(testVO, HttpStatus.EXPECTATION_FAILED); // 417
			
		}
		
//		return ResponseEntity.ok(testVO); // ok => 200(성공코드)
//		return new ResponseEntity<>(testVO, HttpStatus.OK);
		return resEntity;
		
	}
		 
	
}

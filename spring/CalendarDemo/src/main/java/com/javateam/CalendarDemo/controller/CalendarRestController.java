package com.javateam.CalendarDemo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.CalendarDemo.domain.DateDTO;
import com.javateam.CalendarDemo.service.CalendarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalendarRestController {
	
	private final CalendarService calService;
	
	@GetMapping("/calendar")
	public ResponseEntity<List<DateDTO>> calendarAction(@RequestParam(value="year", defaultValue="2025") int year, 
													    @RequestParam(value="month", defaultValue="1") int month) {
		
		log.info("달력 인자 : {}년 {}월", year, month);
		
		ResponseEntity<List<DateDTO>> responseEntity = null;

		try {
		
			List<DateDTO> resultList = calService.getCalendar(year, month);
			
			if (resultList.size() == 0) { // 달력 정보가 존재하지 않으면...
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
				
			} else { // 달력 정보가 존재하면....
				
		   	    responseEntity = new ResponseEntity<>(resultList, HttpStatus.OK);
			} //
			
		} catch (Exception e) {
			
			log.error("calendarAction rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}	
		
		return responseEntity;
	}

}
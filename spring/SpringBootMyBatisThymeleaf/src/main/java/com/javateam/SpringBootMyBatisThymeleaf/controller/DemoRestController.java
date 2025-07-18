package com.javateam.SpringBootMyBatisThymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.SpringBootMyBatisThymeleaf.service.EmployeeService;
import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DemoRestController {
	
	@Autowired
    private EmployeeService service;
	
    @GetMapping("/getOneJson")
    public ResponseEntity<EmployeesVO> getOneJson(@RequestParam("employeeId") int id) {
       
        log.info("getOneJson");
        
        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		ResponseEntity<EmployeesVO> result = null;
		EmployeesVO empVO = service.getOne(id);
		
		try {

			if (empVO != null) {
				
				result = new ResponseEntity<EmployeesVO>(empVO, HttpStatus.OK);
				
			} else {
				
				result = new ResponseEntity<EmployeesVO>(empVO, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			
			log.error("JSON 생성 오류 : " + e);
			result = new ResponseEntity<EmployeesVO>(empVO, HttpStatus.EXPECTATION_FAILED);
		}
		
		// log.info("DemoRestController getOneJson result : " + result);
		
		return result;
    }

}
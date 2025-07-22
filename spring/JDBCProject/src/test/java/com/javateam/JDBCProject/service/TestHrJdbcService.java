package com.javateam.JDBCProject.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.JDBCProject.vo.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestHrJdbcService {
	
	@Autowired
	HrJdbcService hrService;	
	
	EmployeesVO expectedEmployeesVO;
	
	@BeforeEach
	public void setUp() {
		
		expectedEmployeesVO = EmployeesVO.builder()				   		
							   		.employeeId(100)
							   		.firstName("Steven")
							   		.lastName("King")
							   		.email("SKING")
							   		.phoneNumber("515.123.4567")
							   		.hireDate(Date.valueOf("2003-06-17"))
							   		.jobId("AD_PRES")
							   		.salary(24000)				   		
							   		.commissionPct(0)
							   		.managerId(0)
							   		.departmentId(90)
							   		.build();
	}

	@Test
	@DisplayName("HrJdbcService.findById 단위 테스트")
	void test() throws Exception {
	
		EmployeesVO employeesVO = hrService.findById(100);
		
		log.info("employeesVO : " + employeesVO);
		
		assertEquals(expectedEmployeesVO, employeesVO);
	}

}

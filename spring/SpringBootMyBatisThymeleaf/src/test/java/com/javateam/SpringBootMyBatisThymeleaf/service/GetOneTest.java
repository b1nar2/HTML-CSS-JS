package com.javateam.SpringBootMyBatisThymeleaf.service;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

import com.javateam.SpringBootMyBatisThymeleaf.controller.DemoController;
import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

// mockito
import static org.mockito.BDDMockito.*; // given() 메서드

import lombok.extern.slf4j.Slf4j;

// 참고) https://spring.io/guides/gs/testing-web/

// current Spring Boot & jUnit 5
// mockito API reference 
// : https://javadoc.io/doc/org.mockito/mockito-core/5.14.2/org/mockito/Mockito.html

// @SpringBootTest
@WebMvcTest(DemoController.class) // 테스트할 컨트롤러(Controller)(대상 객체) 삽입
// @AutoConfigureMockMvc
@Slf4j
public class GetOneTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
    private EmployeeService service;
	
	@Test
	@DisplayName("getOne 통합 테스트(Integration Test)")
	public void testGetOne() throws UnsupportedEncodingException, Exception {
		
		// given : 목 서비스(mock Service) 객체 전제 조건
		given(service.getOne(100))
		   .willReturn(EmployeesVO.builder()				   		
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
				   		.build());
		
		/////////////////////////////////////////////////////////////////////////
		
		// when : 테스트의 목적을 보여줌
		// 아이디가 100인 인원의 정보(레코드)를 문자열로 가져옵니다.
		String result = mockMvc.perform(get("/getOne/100")) 
					         .andExpect(status().isOk())
					         .andExpect(content().contentType("text/html; charset=UTF-8"))
					         .andDo(print())
					         .andReturn()
					         .getResponse()
					         .getContentAsString();
		
		/////////////////////////////////////////////////////////////////////////
		
		// then : 테스트의 결과를 검증(verification)
		
		log.info("result : " + result);
		
		// 출력되는 html 내용에 "2003-06-17" 포함 여부 점검 
		// assertTrue(result.contains("2003-06-17"));
		assertThat(result.contains("2003-06-17")).isTrue();
	} 
	
} 
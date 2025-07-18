package com.javateam.SpringBootMyBatisThymeleaf.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.javateam.SpringBootMyBatisThymeleaf.controller.DemoController;
import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class GetAllTest {
	
	@Autowired
    private WebApplicationContext wac;
	
	// @Autowired
	private MockMvc mockMvc;
	
    // 목 객체에 테스트할 컨트롤러(Controller) 삽입
    @InjectMocks
    private DemoController demoController;
	
	// 통합 테스트 사전 준비 작업
    @BeforeEach
    public void setup() {
        log.info("before setup");

// 1) old style        
//        mockMvc = MockMvcBuilders.standaloneSetup(demoController)
//				        		 .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가 
//				        		 .alwaysDo(print())
//				        		 .build();

// 2)
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
					    		.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가 
					   		 	.alwaysDo(print())
					   		 	.build();
    } //
		
	@Test
	@DisplayName("getOne 통합 테스트(Integration Test)")
	public void testGetOne() throws UnsupportedEncodingException, Exception {
		
		String result = mockMvc.perform(get("/getOne/100")) 
					         .andExpect(status().isOk())
					         .andExpect(content().contentType("text/html; charset=UTF-8"))
					         .andDo(print())
					         .andReturn()
					         .getResponse()
					         .getContentAsString();
		
		log.info("result : " + result);
		
		// 출력되는 html 내용에 "2003-06-17" 포함 여부 점검 
		assertTrue(result.contains("2003-06-17"));
	}
	
	@Test
	@DisplayName("getOneJson 통합 테스트(Integration Test)")
	public void testGetOneJson() throws Exception {
		
		// given
		EmployeesVO requestVO = EmployeesVO.builder()
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
		
		log.info("날짜 : " + requestVO.getHireDate());
		
		// when + then
		mockMvc.perform(get("/getOneJson").param("employeeId",  requestVO.getEmployeeId() + "")) 
			         .andExpect(status().isOk())
			         .andExpect(content().contentType("application/json; charset=UTF-8"))
//			         .andExpect(jsonPath("$.employeeId").exists())			         
//			         .andExpect(jsonPath("$['lastName']").value("King"))
//			         .andExpect(jsonPath("$.firstName", is("Steven")))			         
//			         .andExpect(jsonPath("$.employeeId").exists())								         
//			         .andExpect(jsonPath("$.firstName").exists())
//			         .andExpect(jsonPath("$.lastName").exists())
//			         .andExpect(jsonPath("$.email").exists())
//			         .andExpect(jsonPath("$.phoneNumber").exists())
//			         .andExpect(jsonPath("$.hireDate").exists())
//    				 .andExpect(jsonPath("$.jobId").exists())
//					 .andExpect(jsonPath("$.salary").exists())
//					 .andExpect(jsonPath("$.commissionPct").exists())
//					 .andExpect(jsonPath("$.managerId").exists())
//					 .andExpect(jsonPath("$.departmentId").exists())			         
					 .andExpect(jsonPath("$.firstName").value(requestVO.getFirstName()))
			         .andExpect(jsonPath("$.lastName").value(requestVO.getLastName()))
			         .andExpect(jsonPath("$.email").value(requestVO.getEmail()))
			         .andExpect(jsonPath("$.phoneNumber").value(requestVO.getPhoneNumber()))
			         .andExpect(jsonPath("$.hireDate", is(requestVO.getHireDate().toString()))) // 날짜 비교시 매우 주의 !
    				 .andExpect(jsonPath("$.jobId").value(requestVO.getJobId()))
					 .andExpect(jsonPath("$.salary").value(requestVO.getSalary()))
					 .andExpect(jsonPath("$.commissionPct").value(requestVO.getCommissionPct()))
					 .andExpect(jsonPath("$.managerId").value(requestVO.getManagerId()))
					 .andExpect(jsonPath("$.departmentId").value(requestVO.getDepartmentId()))		
			         .andDo(print());
	}
	
	@Test
	@DisplayName("getAll 통합 테스트(Integration Test)")
	public void testGetAll() throws UnsupportedEncodingException, Exception{
		
		String result;		
		
		// given
		result = mockMvc.perform(get("/getAll")) 
		         	 .andExpect(status().isOk())
			         .andExpect(content().contentType("text/html; charset=UTF-8"))
			         .andReturn()
			         .getResponse()
			         .getContentAsString();
		
		log.info("result : " + result);
		
		// when
		// HTML의 <table>태그의 하위 태그 <tr>태그의 행의 수가 108(타이틀 + 데이터 레코드 행) 인지를 점검
		// jsoup test
		int count = 0;
		Elements els = Jsoup.parse(result).getAllElements();
		
		for (Element el : els) {
			if (el.nodeName().equals("tr")) count++; 
		}
		
		// then
		log.info("count : " + count);
		
		assertEquals(108, count); // tr 태그의 갯수
		
	    log.info("전체 검색 결과(html 컨텐츠 크기) : "+ result.length());
	}

}
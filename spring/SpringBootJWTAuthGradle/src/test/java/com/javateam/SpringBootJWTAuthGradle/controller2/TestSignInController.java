package com.javateam.SpringBootJWTAuthGradle.controller2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestSignInController {
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
	
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				                .apply(springSecurity())
				                .build();
	}	
	
	@Autowired
	ObjectMapper objectMapper; 
	
	@Test
	@DisplayName("로그인 성공 테스트")
	void testLoginSuccess() throws Exception {
		
		// given : 실제 로그인 데이터
		String userId = "abcd1234";
		String password = "#Abcd1234";
		
		// when : 실제 로그인 점검
		ResultActions actions = mockMvc.perform(post("/sign-api/sign-in")
										.param("userId", userId)
										.param("password", password)
										.with(csrf())) // csrf token
										.andDo(print()) // 테스트 결과 콘솔에 인쇄
										.andExpect(status().isOk());
					
		// then(verify)
		String result = actions.andReturn()
								.getResponse()
								.getContentAsString();
		
		// then(verify) : 로그인 성공시 메시지 확인
		log.info("result : " + result);
		
		JsonNode jsonNode = objectMapper.readTree(result);
		boolean success = Boolean.parseBoolean(jsonNode.get("success").asText());
		String msg = jsonNode.get("msg").asText();
		
		assertTrue(success);
		assertEquals(msg , "Success");
	}
	
	@Test
	@DisplayName("존재하지 않는 아이디로 로그인 실패 테스트")
	void testLoginUserIdFail() throws Exception {
		
		// given : 실제 로그인 데이터
		String userId = "abcd123456789"; // 존재하지 않는 사용자 아이디
		String password = "#Abcd1234"; 
		
		// when : 실제 로그인 점검
		ResultActions actions = mockMvc.perform(post("/sign-api/sign-in")
										.param("userId", userId)
										.param("password", password)
										.with(csrf())) // csrf token
										.andDo(print()) // 테스트 결과 콘솔에 인쇄
										.andExpect(status().isBadRequest()); 
										 // then(verify) : 400 에러 기대 : HttpStatus.BAD_REQUEST : 클라이언트의 잘못된 요청(request)
					
		// then(verify)
		String result = actions.andReturn()
								.getResponse()
								.getContentAsString();

		// then(verify) : 로그인 성공시 메시지 확인
		log.info("result : " + result);
		
		JsonNode jsonNode = objectMapper.readTree(result);
		String failCode = jsonNode.get("code").asText();
		String msg = objectMapper.readTree(result).get("message").asText();
		
		assertEquals("400", failCode);
		assertEquals(msg , "회원 정보가 존재하지 않습니다");
	}
	
	@Test
	@DisplayName("틀린 패쓰워드로 로그인 실패 테스트")
	void testLoginPasswordFail() throws Exception {
		
		// given : 실제 로그인 데이터
		String userId = "abcd1234";
		String password = "#Abcd1234567890"; // 틀린 패쓰워드
		
		// when : 실제 로그인 점검
		ResultActions actions = mockMvc.perform(post("/sign-api/sign-in")
										.param("userId", userId)
										.param("password", password)
										.with(csrf())) // csrf token
										.andDo(print()) // 테스트 결과 콘솔에 인쇄
										.andExpect(status().isUnauthorized()); 
										 // then(verify) : 401 에러 기대 : HttpStatus.UNAUTHORIZED : 로그인 인증 안됨 에러
					
		// then(verify)
		String result = actions.andReturn()
								.getResponse()
								.getContentAsString();

		// then(verify) : 로그인 성공시 메시지 확인
		log.info("result : " + result);
		
		JsonNode jsonNode = objectMapper.readTree(result);
		String failCode = jsonNode.get("code").asText();
		String msg = objectMapper.readTree(result).get("message").asText();
		
		assertEquals("401", failCode);
		assertEquals(msg , "패쓰워드가 일치하지 않습니다");
	}
	
}
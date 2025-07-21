package com.javateam.SpringBootJWTAuthGradle.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.javateam.SpringBootJWTAuthGradle.config.security.JwtAuthenticationFilter;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ChangeProductNameDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductResponseDto;
import com.javateam.SpringBootJWTAuthGradle.mock.WithMockCustomUser;
import com.javateam.SpringBootJWTAuthGradle.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ProductRestController.class)  // 테스트 대상 객체(SUT : System Under Test) 주입
@AutoConfigureMockMvc  // 목 객체 테스트환경 자동 설정
@Slf4j
class TestMockDeleteProductRestController {
	
	@Autowired
    private WebApplicationContext wac; // Web Context 객체 주입(injection)
	
	private MockMvc mockMvc; // Web MVC Mock 객체
	
	@MockitoBean
	private ProductService productService;  // 서비스 목 객체 주입
	
	@MockitoBean
	private JwtAuthenticationFilter jwtAuthenticationFilter;  // JWT 인증 필터 목 객체 주입
	
	ChangeProductNameDto changeProductNameDto;  // 상품 요청 DTO
	ProductResponseDto productResponseDto; // 상품 응답 DTO
	
	@BeforeEach // 테스트 사전 작업
	public void setUp() {
		
		// Web MVC Mock 객체 생성
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				                 .apply(springSecurity())
				                 .build();
	}
	
	@Test
	@DisplayName("상품정보 삭제 Rest 컨트롤러 테스트")  // jUnit 패널에 출력될 내용
	@WithMockCustomUser
	void testDeleteProductProductController() throws Exception {
		
		log.info("상품정보 삭제 테스트");
		
		// given : 가정(전제) 사항 : 삭제할 number(상품 아이디) 등록
		long number = 1L;
		
		// when : 테스트 목적에 해당되는 테스트 대상(SUT(System Under Test) : delete("/productRest"))에 대한 테스트 코드 작성
		// 여기서 x-auth-token 토큰 역시 모의(목 : Mock) 토큰 변수값(aaaaaaaaaa)를 활용합니다.	
		ResultActions actions = mockMvc.perform(delete("/productRest")
									   .param("number", number + "")	
									   .header("x-auth-token", "aaaaaaaaa")
									   .with(csrf())) // CSRF 토큰 주입
				 					   .andDo(print())
									   .andExpect(status().isOk()); // then : Http Status code(200) 검증
		
		// then (verify) : 테스트 목적에 대한 검증 : JSON 리턴값(메시지) 검증
		String result = actions.andReturn()
							   .getResponse()
							   .getContentAsString();
		
		log.info("result : " + result);
		
		assertEquals(result, "정상적으로 삭제되었습니다.");
		
		// then (verify) : 테스트 전제(서비스 메서드)에 대한 검증(verification) 
		verify(productService).deleteProduct(number);
	} //
	
}
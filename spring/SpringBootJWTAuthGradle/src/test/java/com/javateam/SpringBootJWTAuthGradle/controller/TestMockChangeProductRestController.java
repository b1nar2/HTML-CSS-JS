package com.javateam.SpringBootJWTAuthGradle.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.javateam.SpringBootJWTAuthGradle.config.security.JwtAuthenticationFilter;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ChangeProductNameDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.ProductResponseDto;
import com.javateam.SpringBootJWTAuthGradle.mock.WithMockCustomUser;
import com.javateam.SpringBootJWTAuthGradle.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ProductRestController.class) // 테스트 대상 객체(SUT : System Under Test) 주입
@AutoConfigureMockMvc // 목 객체 테스트환경 자동 설정
@Slf4j
class TestMockChangeProductRestController {
	
	@Autowired
    private WebApplicationContext wac; // Web Context 객체 주입(injection)
	
	private MockMvc mockMvc;  // Web MVC Mock 객체
	
	@MockitoBean
	private ProductService productService;  // 서비스 목 객체 주입
	
	@MockitoBean
	private JwtAuthenticationFilter jwtAuthenticationFilter;  // JWT 인증 필터 목 객체 주입
	
	ChangeProductNameDto changeProductNameDto; 

	ProductResponseDto productResponseDto;
	
	@BeforeEach
	public void setUp() {
		
		// Web MVC Mock 객체 생성
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				                 .apply(springSecurity())
				                 .build();
		
		// 상품 요청 DTO 생성
		changeProductNameDto = ChangeProductNameDto.builder()
									.number(1L)
									.name("멜론")
									.stock(10)
									.build();
	}	

	@Test
	@DisplayName("상품정보 수정 Rest 컨트롤러 테스트") // jUnit 패널에 출력될 내용
	@WithMockCustomUser // 테스트용 모의(목 : Mock) 사용자 객체 호출
	void testChangeProductProductController() throws Exception {
		
		log.info("상품정보 수정 테스트");
		
		// given : 테스트 전제 조건 
		// : 본 Controller의 액션 메서드(put("/productRest") URL을 처리하는 메서드)의 기반이 되는 
		// 주입된 Service 목(Mock) 객체의 메서드(productService.changeProductName)를 테스트할 것을 전제로 함.
		given(productService.changeProductName(changeProductNameDto.getNumber(),
											changeProductNameDto.getName(),
											changeProductNameDto.getStock()))
			.willReturn(ProductResponseDto.builder()
										  .name("멜론")
										  .price(3000)
										  .stock(10)
										  .build());
		
		ObjectMapper objectMapper = new ObjectMapper();		
		String content = objectMapper.writeValueAsString(changeProductNameDto);
		
		log.info("content : " + content);
		
		// when : 테스트 목적에 해당되는 테스트 대상(SUT(System Under Test) : put("/productRest"))에 대한 테스트 코드 작성
		// 여기서 x-auth-token 토큰 역시 모의(목 : Mock) 토큰 변수값(aaaaaaaaaa)를 활용합니다.	
		ResultActions actions = mockMvc.perform(put("/productRest")
									   .header("x-auth-token", "aaaaaaaaa")
									   .with(csrf()) // csrf token
									   .contentType(MediaType.APPLICATION_JSON)
									   .content(content))
				 					   .andDo(print())
									   .andExpect(status().isOk());
		
		// then (verify) : 테스트 목적에 대한 검증 : JSON 리턴값 검증
		String result = actions.andExpect(jsonPath("$.name").value(changeProductNameDto.getName()))
							   .andExpect(jsonPath("$.price").value(3000))
							   .andExpect(jsonPath("$.stock").value(changeProductNameDto.getStock()))
							   .andReturn()
							   .getResponse()
							   .getContentAsString();
		
		log.info("result : " + result);

		// then (verify) : 테스트 전제(서비스 메서드)에 대한 검증(verification) 
		verify(productService).changeProductName(changeProductNameDto.getNumber(),
												 changeProductNameDto.getName(),
												 changeProductNameDto.getStock());
	} //
	
}
package com.javateam.demoMyBatis.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.demoMyBatis.domain.TestVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestTestDAO {
	
	@Autowired
	private TestDAO testDAO;
	
	/*
	 * 개별 회원정보 조회 점검(test)
	 * 
	 * :  기대값(expected) VO 생성
	 * => 실제값(actual) VO
	 * => 비교(equals)
	 * => 동등비교 점검
	 */
	@Test
	void testSelectTestById() {
		
		TestVO expectedVO = TestVO.builder()
								  .id("tzso6606")
								  .name("송선희")
								  .address("경기도 성남시 분당구 돌마로 47 이코노 샤르망 4층 그린아카데미별관 410호")
								  .build();
		
		TestVO actualVO = testDAO.selectTestById("tzso6606");
		
//		assertEquals(expectedVO, actualVO);
		assertThat(actualVO).isEqualTo(expectedVO);
		
	}

}

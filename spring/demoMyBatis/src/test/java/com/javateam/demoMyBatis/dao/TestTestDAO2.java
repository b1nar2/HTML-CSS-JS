package com.javateam.demoMyBatis.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.demoMyBatis.dao.impl.TestDAOImpl2;
import com.javateam.demoMyBatis.domain.TestVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestTestDAO2 {
	
	@Autowired
	@Qualifier("testDAO2")
	private TestDAO testDAO;
	
	// 생성자 주입 방식
	//private final TestDAO testDAO;
	
//	@Autowired
//	public TestTestDAO2(TestDAOImpl2 testDAO) {
//		this.testDAO = testDAO;
//	}
	
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
			
//			assertEquals(expectedVO, actualVO);
			assertThat(actualVO).isEqualTo(expectedVO);
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testInsertTest() {
		
		TestVO expectedVO = TestVO.builder()
				  .id("javateam")
				  .name("자바팀")
				  .address("미금역")
				  .build();
		
		testDAO.insertTest(expectedVO);
		
		TestVO actualVO = testDAO.selectTestById("javateam");
		assertThat(actualVO).isEqualTo(expectedVO);
	}

}

package com.javateam.demoMyBatis.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.demoMyBatis.domain.TestVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestTestMyBatisService {

	@Autowired
	private TestMyBatisService testService;
		
	@Transactional // 테스트 트랜잭션 자동 롤백됨
	@Rollback(true) // true => 실제 데이터에는 반영X. 테스트를 위함.
	@Test
	void testInsertTest() {
			
			TestVO expectedVO = TestVO.builder()
									  .id("spirng")
									  .name("봄이")
									  .address("오리역")
									  .build();
			
			assertTrue(testService.insertTest(expectedVO));
	}

}

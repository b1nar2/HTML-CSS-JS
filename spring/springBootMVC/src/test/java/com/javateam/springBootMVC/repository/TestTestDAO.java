package com.javateam.springBootMVC.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springBootMVC.domain.TestEntity;

import lombok.extern.slf4j.Slf4j;



@SpringBootTest
@Slf4j
class TestTestDAO {
	
	@Autowired
	TestDAO testDAO;

	@Test
	@Transactional
	@Rollback(true)
	void test() {
		TestEntity testVO = TestEntity.builder()
									  .id("aaaa1234")
									  .name("java")
									  .address("성남")
									  .build();
		
		TestEntity testVO2 = testDAO.save(testVO);
		
		assertEquals(testVO, testVO2);
	
	}
	
	/*전체 인원수 확인*/
	
	@Test
	void testCount() {
		
		log.info("전체 인원수 확인");
//		testDAO.count();
		
		assertEquals(201,testDAO.count());
	}

}

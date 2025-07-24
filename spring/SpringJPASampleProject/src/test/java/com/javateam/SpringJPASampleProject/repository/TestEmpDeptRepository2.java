package com.javateam.SpringJPASampleProject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.javateam.SpringJPASampleProject.domain.EmpDeptViewEntity;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Slf4j
public class TestEmpDeptRepository2 {

	@Autowired
	private EmpDeptRepository empDeptRepository;
	
	@Test
	@DisplayName("EmpDeptRepository.findByJob 단위 테스트")
	void testFindByJob() {
		
		log.info("EmpDeptRepository.findByJob 단위 테스트");
		
		// given
		String expectedFirstEName = "MARTIN";
		String expectedLastEName = "WARD";
		
		// when
		List<EmpDeptViewEntity> actualEdvEntities 
					= empDeptRepository.findByJob("SALESMAN");
		
		// then
		
		// 레코드 수 점검
		assertEquals(4, actualEdvEntities.size());
		
		// 경계값(처음/끝 레코드 이름) 점검
		assertEquals(expectedFirstEName, actualEdvEntities.get(0).getEname());
		assertEquals(expectedLastEName, actualEdvEntities.get(3).getEname());
	}
	
}
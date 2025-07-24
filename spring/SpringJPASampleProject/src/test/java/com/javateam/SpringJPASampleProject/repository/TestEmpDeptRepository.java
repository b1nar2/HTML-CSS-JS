package com.javateam.SpringJPASampleProject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Optional;

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
public class TestEmpDeptRepository {

	@Autowired
	private EmpDeptRepository empDeptRepository;
	
	@Test
	@DisplayName("EmpDeptRepository.findById 단위 테스트")
	void testFindById() {
		
		log.info("EmpDeptRepository.findById 단위 테스트");
		
		// given
		EmpDeptViewEntity expectedEdvEntity 
					= EmpDeptViewEntity.builder()
				             .empNo(7839)
				             .ename("KING")
				             .job("PRESIDENT")
				             .mgr(null)
				             .hiredate(Date.valueOf("1981-11-17"))
				             .sal(5000F)
				             .comm(null)
				             .deptNo(10)				            
				             .dname("ACCOUNTING")
				             .loc("NEW YORK")
				             .build();
		// 주의) 위의 두 필드(dname, loc)의 자료형이 char(14), char(13) 
		// : 고정 문자열 자료형이므로, 상호 공백 제거 후 비교 가능.
		
		// when
		Optional<EmpDeptViewEntity> actualEdvEntity = empDeptRepository.findById(7839);
		
		// then
		log.info("expectedEdvEntity : " + expectedEdvEntity);
		log.info("actualEdvEntity : " + actualEdvEntity.get());
		
		assertEquals(expectedEdvEntity, actualEdvEntity.get());
	}
	
}
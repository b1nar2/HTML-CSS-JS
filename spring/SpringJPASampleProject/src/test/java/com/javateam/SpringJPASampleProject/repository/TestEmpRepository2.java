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

import com.javateam.SpringJPASampleProject.domain.Emp;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Slf4j
public class TestEmpRepository2 {

	@Autowired
	private EmpRepository empRepo;
		
	@Test
	@DisplayName("EmpRepository.findById 단위 테스트")
	void testFindById() {
		
		log.info("EmpRepository.findById 단위 테스트");
		
		Emp expectedEmp = Emp.builder()
				             .empNo(7839)
				             .ename("KING")
				             .job("PRESIDENT")
				             .mgr(null)
				             .hiredate(Date.valueOf("1981-11-17"))
				             .sal(5000F)
				             .comm(null)
				             .deptNo(10)
				             .build();
		
		Optional<Emp> actualEmp = empRepo.findById(7839);
		
		log.info("expectedEmp : " + expectedEmp);
		log.info("actualEmp : " + actualEmp.get());
		
		assertEquals(expectedEmp, actualEmp.get());
	}
	
}
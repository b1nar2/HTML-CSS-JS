package com.javateam.SpringJPASampleProject.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.javateam.SpringJPASampleProject.domain.Emp;
import com.javateam.SpringJPASampleProject.repository.EmpRepository;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
// @SpringBootTest
@Slf4j
public class TestEmpRepository {

	@Autowired
	private EmpRepository empRepo;
		
	@Test
	@DisplayName("EmpRepository 단위 테스트")
	public void testFindByJob() {
		
		log.info("EmpRepository 단위 테스트");
		
		try {
			
			List<String> empList = empRepo.findByJob("SALESMAN");			
		
			empList.forEach(x -> {
				
				log.info("회원 : " + x.trim());
			});
		
		} catch (Exception e) {
			log.error("testFindByJob error : " + e);
			e.printStackTrace();
		}
	}
}

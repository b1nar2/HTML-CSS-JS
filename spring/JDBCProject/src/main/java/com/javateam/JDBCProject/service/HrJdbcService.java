package com.javateam.JDBCProject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.JDBCProject.dao.HrJdbcDAO;
import com.javateam.JDBCProject.vo.EmployeesVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor // lombok 방식의 생성자 방식 자바빈 의존성정보 주입(inject)
@Slf4j
public class HrJdbcService {
	
	private final HrJdbcDAO dao; // lombok 방식의 생성자 방식 자바빈 의존성정보 주입(inject)
		
	@Transactional(readOnly = true)
	public EmployeesVO findById(int employeeId) throws Exception {
		
		return dao.findById(employeeId);
	}
	
}

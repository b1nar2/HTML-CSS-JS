package com.javateam.JDBCProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.JDBCProject.vo.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HrJdbcDAO {
	
	@Autowired
	DataSource dataSource;
	
	public EmployeesVO findById(int employeeId) {
		
		EmployeesVO employeesVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employees WHERE employee_id = ?";
		
		try {
			
			conn = dataSource.getConnection();			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, employeeId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				employeesVO = EmployeesVO.builder()
							           .employeeId(rs.getInt("employee_id"))
							           .firstName(rs.getString("first_name"))
							           .lastName(rs.getString("last_name"))
							           .email(rs.getString("email"))
							           .phoneNumber(rs.getString("phone_number"))
							           .hireDate(rs.getDate("hire_date"))
							           .jobId(rs.getString("job_id"))
							           .salary(rs.getInt("salary"))
							           .commissionPct(rs.getInt("commission_pct"))
							           .managerId(rs.getInt("manager_id"))
							           .departmentId(rs.getInt("department_id"))
							           .build();
			}
			
		} catch (SQLException e) {
			log.error("DB 연결 오류 : " + e);
			e.printStackTrace();
		}
		
		log.info("DAO empVO : " + employeesVO);
		
		return employeesVO;
	}

}

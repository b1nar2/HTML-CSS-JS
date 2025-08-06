package com.javateam.SpringJstlELDemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.SpringJstlELDemo.domain.Employees;
import com.javateam.SpringJstlELDemo.mapper.EmployeesMapper;

@Repository("employeesDAO")
public class EmployeesDAOImpl implements EmployeesDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<Employees> getEmployeesList() {

		List<Employees> result = new ArrayList<Employees>();
		EmployeesMapper employeesMapper = sqlSession.getMapper(EmployeesMapper.class);
		result = employeesMapper.getEmployeesList();

		return result;
	}

	@Override
	public Employees getMember(int employeeId) {

		return sqlSession.getMapper(EmployeesMapper.class).getMember(employeeId);

		/*
		 * return sqlSession.selectOne("EmployeesMapper.getMember", employeeId);
		 */
	} //

	@Override
	public void insertMember(Employees employees) {
		
		sqlSession.insert("com.javateam.SpringJstlELDemo.domain.EmployeesMapper.insertMember", 
					employees);
	}
	
}

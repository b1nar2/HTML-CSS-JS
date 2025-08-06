package com.javateam.SpringJstlELDemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javateam.SpringJstlELDemo.domain.Employees;

@Mapper
public interface EmployeesMapper {

	 List<Employees> getEmployeesList();
	 
	 Employees getMember(int employeeId);

	 void insertMember(Employees employees);
}
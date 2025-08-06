package com.javateam.SpringJstlELDemo.service;

import java.util.List;

import com.javateam.SpringJstlELDemo.domain.Employees;

public interface EmployeesService {

	List<Employees> getEmployeesList();
	
	Employees getMember(int employeeId);
	
	void insertMember(Employees employees);

}
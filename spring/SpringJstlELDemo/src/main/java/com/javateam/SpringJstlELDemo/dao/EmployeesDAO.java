package com.javateam.SpringJstlELDemo.dao;

import java.util.List;
import com.javateam.SpringJstlELDemo.domain.Employees;

public interface EmployeesDAO {
	
	List<Employees> getEmployeesList();
	
	Employees getMember(int employeeId);
	
	void insertMember(Employees employees);

}
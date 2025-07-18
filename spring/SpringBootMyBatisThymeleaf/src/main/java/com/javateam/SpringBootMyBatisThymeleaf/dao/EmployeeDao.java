package com.javateam.SpringBootMyBatisThymeleaf.dao;

import java.util.List;

import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

public interface EmployeeDao {

	 public List<EmployeesVO> getAll();
	 public EmployeesVO getOne(int id);
	 
}
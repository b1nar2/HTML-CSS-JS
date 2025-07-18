package com.javateam.SpringBootMyBatisThymeleaf.service;

import java.util.List;
import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

public interface EmployeeService {

	 public List<EmployeesVO> getAll();
	 public EmployeesVO getOne(int id);
}

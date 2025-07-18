package com.javateam.SpringBootMyBatisThymeleaf.mapper;
 
import java.util.List;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.javateam.SpringBootMyBatisThymeleaf.vo.EmployeesVO;

@Mapper
public interface EmployeesMapper {
   
    @Select("SELECT EMPLOYEE_ID, "
    		+ "FIRST_NAME, "
    		+ "LAST_NAME, "
    		+ "EMAIL, "
    		+ "PHONE_NUMBER, "
    		+ "HIRE_DATE, "
    		+ "JOB_ID, "
    		+ "NVL(SALARY, 0), "
    		+ "NVL(COMMISSION_PCT, 0), "
    		+ "NVL(MANAGER_ID, 0), "
    		+ "NVL(DEPARTMENT_ID,0) "
    		+ "FROM employees "
    		+ "ORDER BY employee_id")
    List<EmployeesVO> getAll();
   
    @Select("SELECT EMPLOYEE_ID, "
    		+ "FIRST_NAME, "
    		+ "LAST_NAME, "
    		+ "EMAIL, "
    		+ "PHONE_NUMBER, "
    		+ "HIRE_DATE, "
    		+ "JOB_ID, "
    		+ "NVL(SALARY, 0), "
    		+ "NVL(COMMISSION_PCT, 0), "
    		+ "NVL(MANAGER_ID, 0), "
    		+ "NVL(DEPARTMENT_ID,0) "
    		+ "FROM employees "
    		+ "WHERE employee_id = #{employeeId}")
    EmployeesVO getOne(@Param("employeeId") int id);
 
}
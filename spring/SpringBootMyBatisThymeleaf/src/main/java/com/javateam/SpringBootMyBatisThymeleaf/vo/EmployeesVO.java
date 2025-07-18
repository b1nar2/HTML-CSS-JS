package com.javateam.SpringBootMyBatisThymeleaf.vo;
 
import java.sql.Date;

import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class EmployeesVO {
   
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private int salary;
    private int commissionPct;
    private int managerId;
    private int departmentId;
   
}
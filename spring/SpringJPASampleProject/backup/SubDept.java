package com.javateam.SpringJPASampleProject.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
// @Table(name = "DEPT")
public class SubDept extends Dept {
			
	@OneToMany(mappedBy = "dept")
	private List<SubEmp> empList = new ArrayList<SubEmp>();
	
	public List<SubEmp> getEmpList() {
		return empList;
	}

	public void setEmpList(List<SubEmp> empList) {
		this.empList = empList;
	}

}
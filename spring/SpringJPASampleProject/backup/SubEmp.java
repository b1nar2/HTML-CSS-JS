package com.javateam.SpringJPASampleProject.domain;

import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// @Table(name = "EMP")
public class SubEmp extends Emp {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO")
	private Dept dept;

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
		
}
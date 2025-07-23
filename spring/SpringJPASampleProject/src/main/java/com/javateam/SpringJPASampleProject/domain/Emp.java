package com.javateam.SpringJPASampleProject.domain;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emp {
	
	@Id
	@Column(name = "EMPNO", columnDefinition = "사원번호", nullable = false)
	private Integer empNo;
	
	@Column(name = "ENAME", columnDefinition = "사원명")
	private String ename;
	
	@Column(name = "JOB", columnDefinition = "담당업무(직무)")
	private String job;
	
	@Column(name = "MGR", columnDefinition = "사원에 대한 관리자")
	private Integer mgr;
	
	@Column(name = "HIREDATE", columnDefinition = "입사일(고용일)")
	private Date hiredate;
	
	@Column(name = "SAL", columnDefinition = "급여(단위:달러)")
	private Float sal;
	
	@Column(name = "COMM", columnDefinition = "상여금(보너스)")
	private Float comm = 0F; // NULL값에 대한 대응
	
	// @PrimaryKeyJoinColumn
	// @JoinColumn(name = "DEPTNO") // SELF JOIN 관계
	@Column(name = "DEPTNO", columnDefinition = "부서번호", nullable = false, 
			insertable = false, updatable = false)
	// insertable = false, updatable = false => 기본키/외래키 이름 중복 방지
	private Integer deptNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTNO")
	private Dept dept;
		
	public Float getComm() {
		return comm;
	}

	public void setComm(Float comm) {
		
		// NULL값에 대한 대응
		comm = comm == null ? 0 : comm; 
		this.comm = comm;
	}
	
}
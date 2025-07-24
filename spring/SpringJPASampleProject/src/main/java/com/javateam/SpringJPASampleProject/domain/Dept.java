package com.javateam.SpringJPASampleProject.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DEPT")
@Data
public class Dept {
	
	@Id
	@Column(name = "DEPTNO", nullable = false, columnDefinition = "NUMBER(2)") // 부서번호
	private int deptno;
	
	@Column(name = "DNAME", columnDefinition = "CHAR(14)") // 부서명
	private String dname;
	
	@Column(name = "LOC", columnDefinition = "CHAR(13)") // 부서위치(도시)
	private String loc;
	
//	@OneToMany(mappedBy = "dept")
//	private List<Emp> empList = new ArrayList<Emp>();
}

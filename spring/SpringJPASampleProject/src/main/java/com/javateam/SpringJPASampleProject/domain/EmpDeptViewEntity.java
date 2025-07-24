package com.javateam.SpringJPASampleProject.domain;

import java.sql.Date;
import java.util.Objects;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.View;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 뷰(View) 자동 생성시 아래의 구문(@View ....) 을 @Table 애너테이션과 더불어 활용

// 자동 생성 옵션 : application.properties
// spring.jpa.hibernate.ddl-auto=create

// @Subselect : 불변성이며 읽기 전용 엔티티(immutable and read-only entity)

// @View : 자동 생성 옵션 활성화시, 실제 View 생성
// @Subselect : 자동 생성 옵션 활성화시, 실제 View 생성하지 않음

@Entity
@Immutable // 불변 객체(가상 테이블 : 뷰)
@Table(name = "VIEW_EMP_DEPT_JOB")
// @Subselect("SELECT e.empno,  "
@View(query = "SELECT e.empno,  "
			+ "	      e.ename, "
			+ "		  e.job, "
			+ "		  e.mgr, "
			+ "		  e.hiredate, "
			+ "		  e.sal, "
			+ "		  e.comm, "
			+ "		  d.deptno, "
			+ "		  d.dname, "
			+ "		  d.loc "
			+ "	 FROM dept d INNER JOIN "
			+ "	      emp e "
			+ "	   ON d.deptno = e.deptno")
@Synchronize({"dept", "emp"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpDeptViewEntity {
	
	@Id
	@Column(name = "EMPNO", nullable = false)
	private Integer empNo;
	
	@Column(name = "ENAME")
	private String ename;
	
	@Column(name = "JOB")
	private String job;
	
	@Column(name = "MGR")
	private Integer mgr;
	
	@Column(name = "HIREDATE")
	private Date hiredate;
	
	@Column(name = "SAL")
	private Float sal;
	
	@Column(name = "COMM")
	private Float comm;
	
	@Column(name = "DEPTNO", nullable = false)
	private Integer deptNo;
		
	@Column(name = "DNAME")
	private String dname;
	
	@Column(name = "LOC")
	private String loc;

	// 주의) 멤버 필드중 dname, loc 두 필드의 자료형이 char(14), char(13) : 고정 문자열 자료형이므로
    // 비교시 자릿수보다 작으면 공백 채워야 제대로 비교됨. 혹은 상호 공백 제거 후 비교 가능.
	// 아래에서는 공백 제거 후 비교
	//
	// Source -> Generate hashCode() and equals() 메뉴 활용
	//
	// 변경 코드) Objects.equals(dname.trim(), other.dname.trim())	
	// 변경 코드) Objects.equals(loc.trim(), other.loc.trim())
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpDeptViewEntity other = (EmpDeptViewEntity) obj;
		return Objects.equals(comm, other.comm) && Objects.equals(deptNo, other.deptNo)
				&& Objects.equals(dname.trim(), other.dname.trim()) && Objects.equals(empNo, other.empNo)
				&& Objects.equals(ename, other.ename) && Objects.equals(hiredate, other.hiredate)
				&& Objects.equals(job, other.job) && Objects.equals(loc.trim(), other.loc.trim()) && Objects.equals(mgr, other.mgr)
				&& Objects.equals(sal, other.sal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comm, deptNo, dname, empNo, ename, hiredate, job, loc, mgr, sal);
	}

}
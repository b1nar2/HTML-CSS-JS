package com.javateam.SpringJPASampleProject.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;

// 주의 사항) 하위 클래스에 @Entity를 붙이게 되면 부모 클래스의 테이블을 상속하는 별도의 테이블이 생성되므로 DType 발생  
// 참고) https://www.baeldung.com/hibernate-inheritance
// 참고) Entity 상속 : https://ict-nroo.tistory.com/128

// ex) ERROR: net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator ===> 1. 
// PreparedStatement.executeQuery() select e1_0.empno,e1_0.dtype,
// e1_0.comm,e1_0.deptno,e1_0.ename,e1_0.hiredate,e1_0.job,e1_0.mgr,e1_0.sal from emp e1_0 where e1_0.empno=7839
// java.sql.SQLSyntaxErrorException: ORA-00904: "E1_0"."DTYPE": 부적합한 식별자

public class SubDept extends Dept {
			
	@OneToMany(mappedBy = "dept")
	private List<Emp> empList = new ArrayList<Emp>();
	
	public List<Emp> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}

}
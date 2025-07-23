package com.javateam.SpringJPASampleProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javateam.SpringJPASampleProject.domain.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	@Query(value = "SELECT e.empno as empNo, "
				 + "e.ename as ename, "
				 + "e.sal as sal, "
				 + "d.dname as dname, "
				 + "d.loc as loc "
				 + "FROM dept d, emp e "
				 + "WHERE d.deptno = e.deptno "
				 + "AND e.job = :job", nativeQuery = true)
	public List<String> findByJob(@Param("job") String job);
	
}
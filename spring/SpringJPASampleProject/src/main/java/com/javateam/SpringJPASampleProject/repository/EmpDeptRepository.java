package com.javateam.SpringJPASampleProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javateam.SpringJPASampleProject.domain.EmpDeptViewEntity;

public interface EmpDeptRepository extends JpaRepository<EmpDeptViewEntity, Integer> {
	
	List<EmpDeptViewEntity> findByJob(String job);

}
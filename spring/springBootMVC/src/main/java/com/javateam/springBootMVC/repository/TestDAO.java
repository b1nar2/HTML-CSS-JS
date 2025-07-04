package com.javateam.springBootMVC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javateam.springBootMVC.domain.TestEntity;

//import org.springframework.stereotype.Repository;

// @Repository
public interface TestDAO extends JpaRepository<TestEntity, String> {

		// name 필드로 (동등)검색해서 - 그 결과를 name 필드를 기준으로 오름차순 정렬
		// https://arahansa.github.io/docs_spring/jpa.html#jpa.query-methods.query-creation
		List<TestEntity> findByNameOrderByNameAsc(String name);
		
		// name 필드로(유사 :Like) 검색해서 - 그 결과를 name 필드를 기준으로 오름차순 정렬
		List<TestEntity> findByNameLikeOrderByNameAsc(String name);

		List<TestEntity> findByNameContainsOrderByNameAsc(String name);
	
}

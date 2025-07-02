package com.javateam.springBootMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javateam.springBootMVC.domain.TestEntity;

//import org.springframework.stereotype.Repository;

// @Repository
public interface TestDAO extends JpaRepository<TestEntity, String> {

}

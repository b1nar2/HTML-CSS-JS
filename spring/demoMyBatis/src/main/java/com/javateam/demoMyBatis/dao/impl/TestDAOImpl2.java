package com.javateam.demoMyBatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.demoMyBatis.dao.TestDAO;
import com.javateam.demoMyBatis.domain.TestVO;
import com.javateam.demoMyBatis.mapper.TestMapper;

import lombok.extern.slf4j.Slf4j;

@Repository("testDAO2")
@Slf4j
public class TestDAOImpl2 implements TestDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	TestMapper testMapper;
	
	// TestMapper (Interface Mapper) 의존성 정보 주입(injection)
	@Override
	public TestVO selectTestById(String id) {
		
//		return sqlSession.getMapper(TestMapper.class)
//						 .selectTestById(id);
		
		return testMapper.selectTestById(id);
	}
	
	@Override
	public void insertTest(TestVO testVO) {

		// sqlSession.getMapper(TestMapper.class).insertTest(testVO);
		// testMapper.insertTest(testVO);
		testMapper.insertTest2(testVO);
	}
	
}

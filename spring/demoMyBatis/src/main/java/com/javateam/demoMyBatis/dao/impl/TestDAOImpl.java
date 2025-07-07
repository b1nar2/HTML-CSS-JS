package com.javateam.demoMyBatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.demoMyBatis.dao.TestDAO;
import com.javateam.demoMyBatis.domain.TestVO;

import lombok.extern.slf4j.Slf4j;
	

// DAO component(Java Bean, domain)
// DB = 영속(영구) 저장소(Persistent Repository)
	
@Repository("testDAO")
@Slf4j
public class TestDAOImpl implements TestDAO {

	// Spring Bean Container 자동으로 인스턴스(sqlSession)를 생성
	@Autowired
	private SqlSession sqlSession;
//	private SqlSessionTemplate sqlSession;
	
	@Override
	public TestVO selectTestById(String id) {
		
		log.info("--- TestDAO.selectTestById");
		return sqlSession.selectOne("sql_mapper.TestMapper.selectTestById", id);
	}

}


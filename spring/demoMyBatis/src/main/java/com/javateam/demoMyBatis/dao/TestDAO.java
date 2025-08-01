package com.javateam.demoMyBatis.dao;

import com.javateam.demoMyBatis.domain.TestVO;

public interface TestDAO {

	/*
	 * 개별 회원정보 조회
	 
	 * @param id 회원 아이디
	 * @return 회원정보
	 */
	public TestVO selectTestById(String id);
	
	/*
	 * 개별 회원정보 삽입(생성)
	 * @param testVO 회원정보
	 * */
	public void insertTest(TestVO testVO);
}
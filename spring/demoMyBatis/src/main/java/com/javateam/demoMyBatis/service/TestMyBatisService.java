package com.javateam.demoMyBatis.service;

import com.javateam.demoMyBatis.domain.TestVO;

public interface TestMyBatisService {


	/*
	 * 개별 회원정보 조회
	 
	 * @param id 회원 아이디
	 * @return 회원정보
	 */
	public TestVO selectTestById(String id);
	
	
	/*
	 * 개별 회원정보 삽입(생성)
	 * @param testVO 회원정보
	 * @return 저장여부
	 * */
	public boolean insertTest(TestVO testVO);

	
	/*
	 * 개별 회원정보 삽입(생성) : TransactionTemplate 사용
	 * @param testVO 회원정보
	 * @return 저장여부
	 * */
	public boolean insertTestTemplate(TestVO testVO);
	
	
	/*
	 * 개별 회원정보 삽입(생성) : TransactionTemplate 사용 (람다 함수식)
	 * @param testVO 회원정보
	 * @return 저장여부
	 * */
	public boolean insertTestTemplateLambda(TestVO testVO);
	
	
	/*
	 * 개별 회원정보 삽입(생성) : @Transactional
	 * @param testVO 회원정보
	 * @return 저장여부
	 * */
	public boolean insertTestTransactional(TestVO testVO);
	
	
	/*
	 * 개별 회원정보 삽입(생성) : @Transactional 2번쨰
	 * @param testVO 회원정보
	 * */
	public void insertTestTransactional2(TestVO testVO);
	
}

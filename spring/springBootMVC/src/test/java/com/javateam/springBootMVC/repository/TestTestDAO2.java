package com.javateam.springBootMVC.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.javateam.springBootMVC.domain.TestEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Data
class TestTestDAO2 {

//	private static final Logger log
//		= LoggerFactory.getLogger(TestTestDAO2.class);		// => 롬복 사용 안 할 때.
	
	@Autowired
	private TestDAO testDAO;
	

	/*
	 * 레코드들을 페이징 기법으로 나누어서 출력
	 * : 1 페이지 당 5명 분량의 레코드 출력
	 */
	@Test
	void test() {
		
		log.info("페이징 테스트");
//		log.info("testDAO.count : " + testDAO.count());
		
		// 1)
//		List<TestEntity> list
//			= testDAO.findAll().subList(0, 5); // subList(x, y) => x이상 ~ y미만
//		list.forEach(x -> {log.info(x +"");});

		// 2)
		// Pageable ◁--- AbstractPageRequest ◁--- PageRequest
		// 자동 변환(다형성)
//		Page<TestEntity> pages
//			=testDAO.findAll(PageRequest.of(0, 5)); // page -1 (0부터 시작)
//		pages.forEach(x -> {log.info(x +"");});

		// 3) 정렬(sorting, ordering) : name 필드를 기준으로 오름차순 정렬
//		List<TestEntity> list = testDAO.findAll(Sort.by(Direction.ASC, "name")); // 오름차순 정렬
//		// pages.forEach(x -> {log.info(x +"");});
//		list.forEach(x -> {log.info(x +"");});
		
		// 4) 페이징
//		Page<TestEntity> pages
//			= testDAO.findAll(Pageable.ofSize(5)); // 1페이지 분량
//		pages.forEach(x -> {log.info(x +"");});
		
		// 5) 정렬 : 3) 경우와 동의 표현
//		Page<TestEntity> pages
//			= testDAO.findAll(Pageable.unpaged(Sort.by(Direction.ASC, "name")));
//		pages.forEach(x -> {log.info(x +"");});

		// 6) 정렬 : 3) 경우와 동일한 표현
//		List<TestEntity> list
//			= testDAO.findAll(Example.of(new TestEntity()), Sort.by(Direction.ASC, "name"));
//		list.forEach(x -> {log.info(x +"");});

		}

		// 7)
		@Test
		void testFindByNameOrderByNameAsc() {
		
			log.info("testFindByNameOrderByNameAsc test");
			List<TestEntity> list
				= testDAO.findByNameOrderByNameAsc("장성찬");
			
//			log.info("tentity" + list.get(0));
			list.forEach(x -> {log.info(x +"");});
	}
		
		@Test
		void findByNameLikeOrderByNameAsc() {
			
			log.info("findByNameLikeOrderByNameAsc test");
			List<TestEntity> list
				= testDAO.findByNameLikeOrderByNameAsc("김%"); // % => sql에서 사용하는 와일드카드(1자 이상)
//				= testDAO.findByNameLikeOrderByNameAsc("김__"); // _ => 한 글자 표기 : 성(김)+이름 2자(__2개) => 3자 검색
			list.forEach(x -> {log.info(x +"");});
			
		}
		
		@Test
		void testFindByNameContainsOrderByNameAsc() {
			
			log.info("findByNameContainsOrderByNameAsc test");
			List<TestEntity> list
				= testDAO.findByNameContainsOrderByNameAsc("김"); // 자동 "%" 처리 => "%김%"
			list.forEach(x -> {log.info(x +"");});
			
			
			// 보충학습 필요!
			
			// 경계값 : 김나빈~김의수
//			String actualFirstName = list.get(0).getName();
//			String actualLastName = list.get(list.size()-1).getName();
//			
//			assertEquals("김나빈", actualFirstName);
//			assertEquals("김의수", actualLastName);

//			??
//			MarcherAssert.assertThat(actualFirstName, equalTo("김나빈"));
//			assertThat(actualFirstName, equalTo("김나빈"));
//			assertThat(actualLastName, is(equalTo("김의수")));
			
		}

}

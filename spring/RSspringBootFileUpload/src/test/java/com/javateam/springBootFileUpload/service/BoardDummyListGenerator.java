package com.javateam.springBootFileUpload.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.time.LocalDate;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BoardDummyListGenerator {
	
	@Autowired
	private BoardRepository boardRepo;
	
	/**
	 * 게시글 더미(dummy) 목록 생성기 : 삽입 이미지 제외 글정보(random info)로만 구성
	 */
	@Test
	@DisplayName("게시글 더미 목록 생성")
	// @RepeatedTest(value = 10) // ex) 20 * 10번 => 200명
	void test() {
		
		int limit = 100;
		
		BoardEntity boardEntity = null;
		List<String> writers = Arrays.asList(new String[]{"test1234", "java1111", "springjava", "htmlmaker",
											 "reactmania", "projectman", "qaman", "publisher"})
									  .stream().map(x -> x.toString()).toList(); 
		
		String[] contents = { "자바는 순수객체지향언어입니다.",
							  "스프링은 잘 알려진 프레임웍입니다.", 
							  "JPA는 ORM 프레임웍입니다.", 
							  "React는 프론트엔드 기술입니다.", 
							  "JSON는 주로 전송 미디어로 활용됩니다.", 
							  "TCP와 UDP는 스트림이냐 패킷 전송이냐의 차이가 있습니다.", 
							  "Javascript는 ECMA를 기반으로 한 언어입니다.", 
							  "Session의 경우는 WAS에서 관리합니다.", 
							  "클라이언트 저장소로는 캐시, localStorage, sessionStorage, cookie 등이 있습니다.", 
							  "프로젝트에서 요구사항의 정의는 매우 중요합니다." };
		
		int cnt = 0;
		
		for (int i=0; i<limit; i++) {
						
			// 작성자
			String writer = writers.get((int)(Math.random()* 8));
			
			// 글 제목
			String title = contents[(int)(Math.random()* 10)].substring(0, 10 + (int)Math.random() * 10);
			
			// 글 내용
			String content = contents[(int)(Math.random()* 10)];	
			
			// 작성일 : 년월일 
			String year = "202" + (int)(Math.random() * 6);
			int month = 1 + (int)(Math.random() * 12);
			int date = 1 + (int)(Math.random() * 30);
			
			month = year.equals("2025") ? 1 + (int)(Math.random() * 8) : month;
			
			LocalDate localDate = LocalDate.now();
			int nowDate = localDate.getDayOfMonth();
			
			date = year.equals("2025") && month == 8 ? 1 + (int)(Math.random() * nowDate) : date;
			date = month == 2 ? 1 + (int)(Math.random() * 27) : date;
			
			// 작성일 : 시분초 입력
			int hour = 1 + (int)(Math.random() * 24);
			int minute = (int)(Math.random() * 60);
			int second = (int)(Math.random() * 60);
			
			log.info("hour : " + hour);
			log.info("minute : " + minute);
			
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(year), month-1, date, hour, minute, second);
			
			Date boardDate = null;
			
			try {
				
				boardDate = new Date(cal.getTimeInMillis());
				SimpleDateFormat dt = new SimpleDateFormat("yyyy년 M월 d일 HH:mm:ss");
				log.info("boardDate : " + dt.format(boardDate));
				
				cnt++;
			
			} catch (Exception e) {
				
				log.error("날짜 생성 에러 발생 : {}, i = {}", e, i);
				log.info("year : " + year);
				log.info("month : " + month);
				log.info("date : " + date);
			}
			
			boardEntity = BoardEntity.builder()
									 .boardWriter(writer)
									 .boardTitle(title)
									 .boardContent(content)	
									 .boardDate(boardDate)
									 .build();
			
			log.info("boardEntity : " + boardEntity);
			
			boardRepo.save(boardEntity);
			
		} // for
		
		log.info("성공 횟수 : " + cnt);
		
		assertEquals(limit, cnt);
		
	} //

}
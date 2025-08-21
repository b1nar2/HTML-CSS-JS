package com.javateam.CalendarDemo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestCalendarDayOpenAPIService {
	
	@Autowired
	private CalendarDayOpenAPIService calOpenService;

	/**
	 * 특정 날짜에 대한 특정일 출력 점검
	 * 
	 * @throws IOException
	 */
	@Test
	void testGetDayByDate() throws IOException {
		
		String day = calOpenService.getDayByDate("2025", "07", "09");
		
		assertEquals("정보보호의 날", day);
	}
	
	/**
	 * 특정달의 특정일들 출력
	 * 
	 * ex) 2025-07
	 * 
	 * 2025-07-14=북한이탈주민의 날
     * 2025-07-11=인구의 날
	 * 2025-07-27=유엔군 참전의 날
     * 2025-07-09=정보보호의 날
	 */
	@Test
	void testGetDay() throws IOException {
		
		Map<LocalDate, String> map = calOpenService.getDayByDate("2025", "07");
		
		map.entrySet().forEach(x -> {
			log.info("" + x);
		});
	}
	
	/**
	 * 음력일을 양력일로 변환 : 석가탄신일
	 */
	@Test
	void testToSolarDateByLunarDate() {
		
		// 석가탄신일 : 음력 4월 8일 => 2025년 기준 양력 5월 5일 
		String solarDate = calOpenService.toSolarDateByLunarDate(2025, 4, 8);
		
		log.info("2025년 음력 4월 8일 => 양력 {}", solarDate);		
	}
	
	/**
	 * 음력일을 양력일로 변환 : 구정설
	 */
	@Test
	void testToSolarDateByLunarDate2() {
		
		// 구정설 : 음력 1월 1일 => 2025년 기준 양력 1월 29일 
		String solarDate = calOpenService.toSolarDateByLunarDate(2025, 1, 1);
		
		log.info("2025년 음력 1월 1일 => 양력 {}", solarDate);		
	}
	
	/**
	 * 음력일을 양력일로 변환 : 추석
	 */
	@Test
	void testToSolarDateByLunarDate3() {
		
		// 구정설 : 음력 8월 15일 => 2025년 기준 양력 10월 06일 
		String solarDate = calOpenService.toSolarDateByLunarDate(2025, 8, 15);
		
		log.info("2025년 음력 8월 15일 => 양력 {}", solarDate);
	}
	
	/**
	 * 특정일에 대한 국경일 이름 조회
	 */
	@Test
	void testGetNationalHodidayByDate() {
		
		// 어린이날, 석가탄신일 : 2025년 5월 5일 => 어린이날/석가탄신일
		log.info("2025년 5월 5일 => {}", calOpenService.getNationalHodidayByDate("2025", "05", "05"));
	}
		
}
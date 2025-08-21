package com.javateam.CalendarDemo.service;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.javateam.CalendarDemo.domain.DateDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarService {
	
	@Autowired
	private CalendarDayOpenAPIService calOpenService;
	
	private int month[] = {31,28,31,30,31,30,31,31,30,31,30,31}; // 평년달
		
	private int lmonth[] = {31,29,31,30,31,30,31,31,30,31,30,31}; // 윤년달
	
	/**
	 * 윤년 점검 메서드
	 * 
	 * @param year 年
	 * @return 윤년 여부
	 */
	public boolean isLeapYear(int year){
		
		boolean flag = false;
		
		if( (0 == (year % 4) && 0 != (year %100)) || 0 == year%400 )
			flag = true;
		
		return flag;
	} // 
	
	/**
	 * 1 ~ year 까지 윤년의 회수 구하기
	 * 
	 * @param year 年
	 * @return 윤년 회수
	 */
	public int howManyLeapYear(int year){
		
		int count = 0;
		
		for(int i = 1; i <=year; i++) {
			
			if (isLeapYear(i) == true) {
				count++;
			} // 
		} // for
		
		return count;
	} //
	
	/**
	 * 특정달 일수 환산 메서드
	 * 
	 * ex) 2020/3/25일이면 (2020/1/1 ~2020/2/29)일 총 일수 + 25
	 * 
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return
	 */
	public int howManyDaysInYearsMonth(int year, 
									   int month, 
									   int day){
		int count = day;
		
		if (isLeapYear(year) == true){ // 윤년 여부 점검
			
			for(int i = 0; i < month-1; i++){
				count+=this.lmonth[i];
			}
			
		} else {
			
			for(int i = 0; i < month-1; i++){
				count+=this.month[i];
			} //
			
		} // 
		
		return count;
	}
	
	/**
	 * 특정달의 시작일의 요일 환산 메서드
	 * ex) 시작날짜가 0→일,1→월,2→화,3→수,4→목,5→금,6→토
	 * 
	 * @param year 年
	 * @param month 月
	 * @return 
	 */
	//
	public int startDayInCal(int year, int month){ // 년 월 1일의 첫날 
		
		int count=0;
		int leapYear = howManyLeapYear(year-1);
		int howManyDaysInYear = howManyDaysInYearsMonth(year,month,1);
		
		count=((leapYear)*2+(year-1-leapYear)+howManyDaysInYear);
		
		return count % 7;
	}
	
	/**
	 * 달의 마지막날 여부 환산 메서드
	 * 
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 달의 마지막날 여부
	 */
	public boolean isLastDay(int year, 
						     int month,
						     int day){ //마지막날
		
		boolean flag = false;
		
		if(!isLeapYear(year)){
			
			if(day==this.month[month-1]){ //평년 2월→28일
				flag = true;
			}
			
		} else {
			
			if(day==this.lmonth[month-1]){ //윤년 2월→29일
				flag = true;
			}
		} //
		
		return flag;
	} // 
	
	
	/**
	 * 달력 출력 메서드
	 * 
	 * @param year 年
	 * @param month 月
	 */
	public void printCalendar(int year, int month){
		
		int linecheck = 0; // 날짜를 처음 찍는 위치 지정하는 변수
		String temp = ""; // 처음 문자 간격
		
		// 요일 표제 인쇄
		System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
		linecheck = startDayInCal(year, month);// 1일(첫날) 요일 점검
		
		for(int j = 0; j < linecheck; j++){
			temp += "\t";  // 1일이 수요일이면 탭(들여쓰기) 3번(일,월,화)
		}
		
		System.out.print(temp);
		
		LocalDate localDate = LocalDate.of(year, month, 1);
		int count = localDate.lengthOfMonth();
		// 특정달 일수 : 그 달에 몇일이 있는가, 28, 29, 30, 31?
		
		for(int i = 1; i<= count; i++){
			
			System.out.print(i + "\t");
			linecheck++;
			
			if(linecheck == 7)  { // 토요일이면
				
				if(this.isLastDay(year,month,i)){
					return ; // 마지막 날이 끝나면 줄바꿈 불필요
				}
				
				System.out.println();
				linecheck = 0;
			}//if
			
		}//for
		
		System.out.println();
		
	}//printCalendar
	
	public List<DateDTO> getCalendar(int year, int month) {
		
		// 달력 컴포넌트에서 나올 수 있는 그리드(grid)의 최대 행수 : 6줄 ex) 2025년 8월의 경우
		// 달력 컴포넌트에서 나올 수 있는 그리드(grid)의 총 열수 : 7열 (요일 수 : 일 ~ 토)
		// 달력 컴포넌트의 행열의 총 요소수(빈(blank) 셀(cell) 포함) : 6 * 7 = 42개 고정 그리드(grid)
		
		// 일, 날 => 15, 광복절
		// 국경일/휴일 데이터사전 별도 구성 
		// ex) 추석과 같은 변동일자(음력 및 정부시책에 따라 달라지는 임시 공휴일 등)는 별도 입력 필요
		// 임시 공휴일 등 변동 휴일에 대한 것은 별도 입력 가능하도록 설정
		List<DateDTO> resultList = new ArrayList<>();
		
		// 첫날의 요일 파악
		LocalDate localDate = LocalDate.of(year, month, 1);
		// log.info("{}년 {}월 첫날 : {}", year, month, localDate.atStartOfDay());		
		// log.info("{}년 {}월 첫날 : {}", year, month, localDate.atStartOfDay().getDayOfMonth());
		// log.info("{}년 {}월 첫날 요일 : {}", year, month, localDate.atStartOfDay().getDayOfWeek());
		// log.info("{}년 {}월 첫날 List 순번 : {}", year, month, localDate.atStartOfDay().getDayOfWeek().getValue());
		// log.info("{}년 {}월 마지막날 : {}", year, month, localDate.lengthOfMonth());
		
		// 6*7 달력 행렬(그리드)
		// 첫째날 시작 순번(index) : localDate.atStartOfDay().getDayOfWeek().getValue()
		int startDateIdx = localDate.atStartOfDay().getDayOfWeek().getValue();
		
		// 마지막날 순번(index) : localDate.lengthOfMonth() + localDate.atStartOfDay().getDayOfWeek().getValue()
		int endDateIdx = startDateIdx + localDate.lengthOfMonth() - 1;
		
		log.info("{}년 {}월 index : {} ~ {}", year, month, startDateIdx, endDateIdx);
		
		// 첫날의 순번(index) => 리스트의 첫 요소 ex) Friday => 5
		
		DateDTO dateDTO = null;
		LocalDate idxDate = null;
		int dayKind = 0; // ex) 해당일 없는 셀(공셀) = -1, 일반일 = 0, 국경일(ex. 광복절) = 1, 특정 기념일(ex. 인구의 날) = 2    
		
		for (int i = 0; i < 42; i++) {
			
			if (i >= startDateIdx && i <= endDateIdx) { // 특정달 시작일 ~ 끝일
				
				log.info("i = " + i);
				
				idxDate = LocalDate.of(year, month, i - startDateIdx + 1);
				String specDay = "";
				String nationalHoliday = "";
				
				try {
					
					log.info("날짜 : {}", idxDate);
				
					String sMonth = month < 10 ? "0" + month : month + "";
					String sDate = (i - startDateIdx + 1) < 10 ? "0" + (i - startDateIdx + 1) : (i - startDateIdx + 1) + "";
					
					// 특정 기념일 점검
					specDay = calOpenService.getDayByDate(year+"", sMonth, sDate);					
					log.info("specDay : " + specDay);
					
					// 국경일(공휴일) 점검
					nationalHoliday = calOpenService.getNationalHodidayByDate(year+"", sMonth, sDate);
					log.info("nationalHoliday : " + nationalHoliday);
					
					// 특정 기념일과 국경일이 겹칠 경우 => 병기(동시 표기)
					if (nationalHoliday != null && specDay != null) {
						specDay = !specDay.equals("") && !nationalHoliday.equals("") ? specDay + "/" + nationalHoliday : specDay;
					}
					
					// 특정 기념일 미해당하면서 국경일에 해당할 경우 
					if (nationalHoliday != null) { //  && specDay == null
						specDay = nationalHoliday;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				// 국경일/특정기념일 점검 필드 할당								
				if (nationalHoliday != null && (nationalHoliday.equals("") == false)) {
					dayKind = 1;
				} else if (specDay != null && specDay.equals("") == false) {
					dayKind = 2;
				} else {
					dayKind = 0;
				}
				
				dateDTO = DateDTO.builder().dateIdx(i).dayName(specDay).localDate(idxDate).dayKind(dayKind).build();
				
			} else {
				
				idxDate = null;
				dateDTO = DateDTO.builder().dateIdx(i).dayName("").dayKind(-1).build();
			}
			
			resultList.add(dateDTO);
			
		} // for		
		
		return resultList;		
	}

}
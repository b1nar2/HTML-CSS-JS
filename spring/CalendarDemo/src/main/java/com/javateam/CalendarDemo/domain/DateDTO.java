package com.javateam.CalendarDemo.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateDTO {
	
	private int dateIdx = 0; // 6*7 달력 그리드에서의 index(순번)
	
	private LocalDate localDate; // 해당 날짜
	
	private String dayName = ""; // ex) 광복절
	
	private int dayKind; // ex) 해당일 없는 셀(공셀) = -1, 일반일 = 0, 국경일(ex. 광복절) = 1, 특정 기념일(ex. 인구의 날) = 2    

}
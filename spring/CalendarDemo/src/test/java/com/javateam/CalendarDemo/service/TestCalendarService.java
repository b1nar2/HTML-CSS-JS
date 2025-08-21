package com.javateam.CalendarDemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.CalendarDemo.domain.DateDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestCalendarService {
	
	@Value("${govServiceKey}")
	private String serviceKey;
	
	@Autowired
	private CalendarService calendarService; 

	@Test
	void testPrintCalendar() {
		
		calendarService.printCalendar(2025, 7);
	}
	
	@Test
	void testGetCalendar() {
		
		List<DateDTO> list = calendarService.getCalendar(2025, 7);
		
		list.forEach(x -> {
			log.info("" + x);
		});
	}
	
	@Test
	void testDatesMethods() {
		
		LocalDate date = LocalDate.of(2012, 2, 1);
		
		//log.info("특정 지정일 : " + date.atStartOfDay());
		//log.info("특정 지정일 날 : " + date.getDayOfMonth());
		log.info("특정달의 마지막날 : " + date.lengthOfMonth());
		
	}
	
	@Test
	void testOpenCalendatAPI() throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getAnniversaryInfo"); /*URL*/
        
		// urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("31", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2025", "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode("07", "UTF-8")); /*월*/
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        
        System.out.println(sb.toString());
	}

}
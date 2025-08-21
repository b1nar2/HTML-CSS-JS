package com.javateam.CalendarDemo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Month;
//import java.time.chrono.ThaiBuddhistChronology;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.github.usingsky.calendar.KoreanLunarCalendar;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarDayOpenAPIService {
	
	@Value("${govServiceKey}")
	private String serviceKey;
	
	/**
	 * 국경일(국경 휴무일) 
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public String getNationalHodidayByDate(String year, String month, String date) {
				
		Map<String, String> map = new HashMap<>();
		
		// 한국의 공휴일 : https://ko.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98_%EA%B3%B5%ED%9C%B4%EC%9D%BC
		// 양력 고정일(무변동일)
		map.put(year + "-01-01", "신정 설날");
		map.put(year + "-03-01", "삼일절");
		map.put(year + "-05-05", "어린이날");
		map.put(year + "-06-06", "현충일");
		// map.put("07-17", "제헌절");
		map.put(year + "-08-15", "광복절");
		map.put(year + "-10-03", "개천절");
		map.put(year + "-10-09", "한글날");
		map.put(year + "-12-25", "크리스마스");
		
		// 음력/양력 변환 계산
		// 음력 변동일
		// 석가탄신일 : 음력 4월 8일 
		
		// 기존 국경일과 중복 여부 점검
		// ex) 2025년 5월 5일은 어린이날 이면서 석가탄신일 => 병기(동시 표기) 어린이날/석가탄신일
		String buddahDay = this.toSolarDateByLunarDate(Integer.parseInt(year), 4, 8);
		
		map.computeIfPresent(buddahDay, (k, v) -> {
			return v += "/" + "석가탄신일";
		});
		
		map.computeIfAbsent(buddahDay, (k) -> {
			return "석가탄신일";
		});
		
		// 구정설 : 당일만 반영 => 추후) 정부 시책에 따른 정책적 휴무기간 지정여부는 추후 반영 ex) 5~6일 기간 반영 등		
		String lunarNewYearDay = this.toSolarDateByLunarDate(Integer.parseInt(year), 1, 1);
	
		map.computeIfPresent(lunarNewYearDay, (k, v) -> {
			return v += "/" + "구정설(당일)";
		});
		
		map.computeIfAbsent(lunarNewYearDay, (k) -> {
			return "구정설(당일)";
		});
		
		// 추석 : 당일만 반영 => 추후) 정부 시책에 따른 정책적 휴무기간 지정여부는 추후 반영 ex) 5~6일 기간 반영 등
		String chuseok = this.toSolarDateByLunarDate(Integer.parseInt(year), 8, 15);
		
		map.computeIfPresent(chuseok, (k, v) -> {
			return v += "/" + "추석(당일)";
		});
		
		map.computeIfAbsent(chuseok, (k) -> {
			return "추석(당일)";
		});
		
		String selectedDate = year + "-" + month + "-" + date;
		
		return map.get(selectedDate);
	}
	
	/**
	 * 년월일 정보로 특정일 출력 ex) 인구의 날
	 * 
	 *  XML 정보
	 *  
	 *  <?xml version="1.0" encoding="UTF-8"?>
	 *  <response>
	 *  <header>
	 *    <resultCode>00</resultCode>
	 *    <resultMsg>NORMAL SERVICE.</resultMsg>
	 *  </header>
	 *  <body>
	 *    <items>
	 *      <item>
	 *        <dateKind>02</dateKind>
	 *        <dateName>2·28 민주운동 기념일</dateName>
	 *        <isHoliday>N</isHoliday>
	 *        <locdate>20190228</locdate>
	 *        <seq>1</seq>
	 *      </item>
	 *    </items>
	 *    <numOfRows>10</numOfRows>
	 *    <pageNo>1</pageNo>
	 *    <totalCount>1</totalCount> 
	 *  </body>
 	 *</response>  
	 *  
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @throws IOException
	 */
	public String getDayByDate(String year, String month, String date) throws IOException {
		
		return getDayByDate(year, month).get(LocalDate.of(Integer.parseInt(year), 
														  Integer.parseInt(month), 
														  Integer.parseInt(date)));        
	}
	
	/**
	 * 년월 정보로 특정일 출력 ex) 인구의 날
	 * 
	 *  XML 정보
	 *  
	 *  <?xml version="1.0" encoding="UTF-8"?>
	 *  <response>
	 *  <header>
	 *    <resultCode>00</resultCode>
	 *    <resultMsg>NORMAL SERVICE.</resultMsg>
	 *  </header>
	 *  <body>
	 *    <items>
	 *      <item>
	 *        <dateKind>02</dateKind>
	 *        <dateName>2·28 민주운동 기념일</dateName>
	 *        <isHoliday>N</isHoliday>
	 *        <locdate>20190228</locdate>
	 *        <seq>1</seq>
	 *      </item>
	 *    </items>
	 *    <numOfRows>10</numOfRows>
	 *    <pageNo>1</pageNo>
	 *    <totalCount>1</totalCount> 
	 *  </body>
 	 *</response>  
	 *  
	 * @param year
	 * @param month
	 * @return
	 * @throws IOException
	 */
	public Map<LocalDate, String> getDayByDate(String year, String month) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getAnniversaryInfo"); /*URL*/
        // urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("31", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(year, "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(month, "UTF-8")); /*월*/
        
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
        
        log.info("XML 출력");
        // System.out.println(sb.toString());
        // System.out.println(prettyFormat(sb.toString(), 2));
        
        //////////////////////////////////////////////////
        
        // 일자에 따른 "특정일" (ex. 정보보호의 날 등) 출력
        Map<LocalDate, String> map = new HashMap<>();
        
        try {

        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	
        	InputSource inputSource = new InputSource(new StringReader(sb.toString()));
        	
        	Document doc = builder.parse(inputSource);
        	
        	int len = doc.getElementsByTagName("locdate").getLength();
        	System.out.println("len : " + len);        	
        	
        	int size = Integer.parseInt(doc.getElementsByTagName("totalCount").item(0).getTextContent());
        	
        	log.info("size : " + size);
        	
        	for (int i=0; i<size; i++) {
        		 
        		String locdate = doc.getElementsByTagName("locdate").item(i) != null ? 
        						 doc.getElementsByTagName("locdate").item(i).getTextContent() : "";
        		
        		String dateName = doc.getElementsByTagName("dateName").item(i) != null ? 
        						  doc.getElementsByTagName("dateName").item(i).getTextContent() : "";
        		
        		log.info("--- locdate : " + locdate);
        		
        		LocalDate localDate = LocalDate.of(Integer.parseInt(locdate.substring(0,4)), 
        										   Month.of(Integer.parseInt(locdate.substring(4,6))), 
												   Integer.parseInt(locdate.substring(6)));
        		
        		map.put(localDate, dateName);
        	}
        	
        	map.entrySet().forEach(x -> System.out.println(x));
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return map;        
	}
	
	/**
	 * XML 포맷터 
	 * 
	 * @param input
	 * @param indent 들여쓰기
	 * @return
	 */
	// 출처 : https://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java
	private static String prettyFormat(String input, int indent) {
		
	    try {
	        
	    	Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
	        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	        
	    } catch (Exception e) {
	        throw new RuntimeException(e); 
	    }
	    
	}
	
	/**
	 * 음력을 양력으로 변환
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public String toSolarDateByLunarDate(int year, int month, int date) {
		
		KoreanLunarCalendar calendar = KoreanLunarCalendar.getInstance();
				
		// param : year(년), month(월), day(일), 윤달(Intercalation) 여부
		calendar.setLunarDate(year, month, date, calendar.isIntercalation());

		// Lunar Date (ISO format) : 음력
		// System.out.println(calendar.getLunarIsoFormat());
		
		// Solar Date (ISO format) : 양력
		// System.out.println(calendar.getSolarIsoFormat());
		
		// Korean GapJa String : 한국식 갑자 표기
		// ex) 2025년 4월 8일 => 양력 을사년 경진월 정미일
		// System.out.println(calendar.getGapjaString());
		
		// Chinese GapJa String : 중국식 갑자 표기
		// System.out.println(calendar.getChineseGapJaString());
		
		return calendar.getSolarIsoFormat();
	}

}
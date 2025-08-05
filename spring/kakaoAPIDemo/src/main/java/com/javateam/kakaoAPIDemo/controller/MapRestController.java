package com.javateam.kakaoAPIDemo.controller;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MapRestController {
	
	// 카카오 REST 키는 세션 로그인 아이디를 활용하여 key를 저장하였다가 DB에서 로딩하여 문자열 연결 처리할 수도 있습니다.   
	// String KAKAO_APIKEY = "KakaoAK KAKAO REST API KEY"; // REST API KEY
	@Value("${KAKAO_APIKEY}")
	private String KAKAO_APIKEY;

	/**
	 * KAKAO 길찾기 proxy REST controller Example Mapping   
	 * 
	 * 참고) https://developers.kakaomobility.com/docs/navi-api/directions/
	 * 
	 * @param X1 출발점 경도 좌표 ex) 127.10764191124568 (일단 예시에서는 편의상 동등한 좌표를 기입함)
	 * @param Y1 출발점 위도 좌표 ex) 37.402464820205246 (일단 예시에서는 편의상 동등한 좌표를 기입함)
	 * @param X2 도착점 경도 좌표 ex) 127.11056336672839 (일단 예시에서는 편의상 동등한 좌표를 기입함) 
	 * @param Y2 도착점 위도 좌표 ex) 37.39419693653072 (일단 예시에서는 편의상 동등한 좌표를 기입함)
	 * @param Y3 도로 포인트 좌표 ex) 127.17354989857544 (일단 예시에서는 편의상 동등한 좌표를 기입함)
	 * @param Y3 도로 포인트 좌표 ex) 37.36629687436494 (일단 예시에서는 편의상 동등한 좌표를 기입함)
	 * @return KAKAO JSON 정보
	 * 
	 * ex) {
	  "trans_id": "019873b2f0b070fd9abc48d5783446be",
	  "routes": [
	    {
	      "result_code": 0,
	      "result_msg": "길찾기 성공",
	      "summary": {
	        "origin": {
	          "name": "",
	          "x": 127.10763058573032,
	          "y": 37.40246478787756
	        },
	        "destination": {
	          "name": "",
	          "x": 127.11055204255132,
	          "y": 37.394196904517194
	        },
	        "waypoints": [
	          {
	            "name": "",
	            "x": 127.17353858063272,
	            "y": 37.3662968484953
	          }
	        ],
	        "priority": "RECOMMEND",
	        "bound": {
	          "min_x": 127.10699672876241,
	          "min_y": 37.35782058991495,
	          "max_x": 127.17722774993561,
	          "max_y": 37.40579895642803
	        },
	        "fare": {
	          "taxi": 22500,
	          "toll": 0
	        },
	        "distance": 19923,
	        "duration": 3516
	      },
	      "sections": [
	        {
	          "distance": 10440,
	          "duration": 1837,
	          "bound": {
	            "min_x": 127.1722891944854,
	            "min_y": 37.35821336047281,
	            "max_x": 127.1733795500364,
	            "max_y": 37.40537861644436
	          },
	          "roads": [
	            {
	              "name": "판교역로241번길",
	              "distance": 186,
	              "duration": 52,
	              "traffic_speed": 13,
	              "traffic_state": 2,
	              "vertexes": [
	                127.10763122680424,
	                37.40241072822385,
	                127.10675008536649,
	                37.4024220962682,
	                127.10658067621765,
	                37.40242081631585,
	                127.10654658043828,
	               
	            
	 * 
	 * ...(후략)...
	 * 
	 */
	@GetMapping("/proxy/{X1}/{Y1}/{X2}/{Y2}/{X3}/{Y3}")
	public ResponseEntity<String> proxy(@PathVariable("X1") String X1, 
										@PathVariable("Y1") String Y1,
										@PathVariable("X2") String X2, 
										@PathVariable("Y2") String Y2,
										@PathVariable("X3") String X3, 
										@PathVariable("Y3") String Y3) 
	{
		// API Doc Link) https://developers.kakaomobility.com/docs/navi-api/directions/
		
		// curl -v -X GET "https://apis-navi.kakaomobility.com/v1/directions?
		// origin=127.10764191124568,37.402464820205246,angle=270
		// &destination=127.11056336672839,37.39419693653072
		// &summary=false
		// &waypoints=127.17354989857544,37.36629687436494
		// &priority=RECOMMEND
		// &car_fuel=GASOLINE
		// &car_hipass=false
		// &alternatives=false
		// &road_details=false" 
		// -H "Authorization: KakaoAK REST_KEY"
		
		log.info("KAKAO_APIKEY : {}", KAKAO_APIKEY);
		
		String reqUrl = "https://apis-navi.kakaomobility.com/v1/directions"; // basic URL
		
		// 타임아웃 설정 : HttpComponentsClientHttpRequestFactory 객체 생성
        HttpComponentsClientHttpRequestFactory factory 
        		= new HttpComponentsClientHttpRequestFactory();
        
        factory.setConnectTimeout(5000); // 연결 타임아웃(시간제한) 5초
        factory.setReadTimeout(5000); // 읽기 타임아웃(시간제한) 5초

        // URL 같은 기본적인 자바의 클래스를 활용할 수도 있지만  Apache HttpComponents API를 활용할 수도 있습니다.
        // https://hc.apache.org/httpcomponents-client-5.5.x/index.html
        HttpClient httpClient = HttpClientBuilder.create()
        		                                 .build();        
        
        factory.setHttpClient(httpClient);
		
        // 실질적인 proxy 컨트롤러의 핵심은 RestTemplate입니다.
        // 웹 브라우저에서 노출 위험이 있는 정보(가령, 개인 REST key 등)을 백단(backend)에서 처리하기 때문에 
        // 보다 안전하게 REST Service를 구현할 수 있습니다.
        // RestTemplate
		RestTemplate restTemplate = new RestTemplate(factory);

		// HTTP header 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add("Authorization", "KakaoAK " + KAKAO_APIKEY);
		
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
		// curl -v -X GET "https://apis-navi.kakaomobility.com/v1/directions?
		// origin=127.10764191124568,37.402464820205246,angle=270
		// &destination=127.11056336672839,37.39419693653072
		// &summary=false
        // &waypoints=127.17354989857544,37.36629687436494
		// &priority=RECOMMEND
		// &car_fuel=GASOLINE
		// &car_hipass=false
		// &alternatives=false
		// &road_details=false" 
		// -H "Authorization: KakaoAK REST_KEY"
        
        // REST Service URL 설정 : 기본 API 주소 및 인자 설정
        String url = UriComponentsBuilder.fromUriString(reqUrl)
							.queryParam("origin", X1 + "," + Y1 + ",angle=270")
							.queryParam("destination", X2 + "," + Y2)
							.queryParam("summary", "false")
							.queryParam("waypoints", X3 + "," + Y3)
							.queryParam("priority", "RECOMMEND")
							.queryParam("car_fuel", "GASOLINE")
							.queryParam("car_hipass", "false")
							.queryParam("alternatives", "false")
							.queryParam("road_details", "false")
							.build()
							.toUriString();
		
		// 응답(response) 정보
		ResponseEntity<String> response 
			= restTemplate.exchange(url, HttpMethod.GET, entity, String.class); 
		
		log.info("response : " + response);
		log.info("response Code : " + response.getStatusCode());
		log.info("response Body : " + response.getBody());
		
		// 추후에 할 수 있다면 try ~ catch 구문을 두어서 HTTP error 들에 대비된 코드를 삽입하여 보완하는 것이 좋습니다.
		return response;
	}
	
}
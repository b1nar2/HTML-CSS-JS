package com.javateam.springBootFileUpload.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageService {
	
	// ex) imgUploadPath = /board/image/
	public List<String> getImageList(String str, String imgUploadPath) {

		log.info("BoardService.getImageList");
		
		List<String> imgList = new ArrayList<>(); // 이미지 파일들
		
		if (str.contains(imgUploadPath) == false) { // 이미지 미포함
			
			log.info("이미지가 전혀 포함되어 있지 않습니다.");
			
		} else {

			// 포함된 전체 이미지 수 : 이 한계량 만큼 검색  => 카운터에 반영
			int imgLen = StringUtils.countOccurrencesOf(str, imgUploadPath);
			
			log.info("imgLen : " + imgLen);
			
			// 이미지 검색 카운터 설정 : 이미지 검색할 횟수
			int count = 0;  
			
			int initPos = str.indexOf(imgUploadPath);
			log.info("첫 발견 위치 : " + initPos);
			
			// 추출된 문자열 : 반복문에서 사용
			String subStr = str;
			
			while (count < imgLen) {
				
				initPos = subStr.indexOf(imgUploadPath);
				
				// 이미지 파일만 추출 (첫번째)
				// <img src="http://localhost:8181/springBootFileUpload/image/react_logo_7a0481e3ca4f490e98b3d72cd8ff752d.png" 
				// "/springBootFileUpload/image/".length()
				initPos += imgUploadPath.length();
				log.info("이미지 파일 시작 위치 : " + initPos);
				
				// 추출된 문자열
				
				// ex) react_logo_7a0481e3ca4f490e98b3d72cd8ff752d.png 
				// ..../springBootFileUpload/image/react_logo_7a0481e3ca4f490e98b3d72cd8ff752d.png" 에서 추출 
				// : board_img_tbl 테이블로 조장  
				subStr = subStr.substring(initPos);
				
				log.info("subStr : " + subStr);
				
				// 첫번째 " (큰 따옴표) 위치 검색하여 순수한 파일명만 추출
				int quotMarkPos = subStr.indexOf("\"");
				
				// 이미지 파일 끝 검색하여 이미지 파일명/확장자 추출
				// 이미지 끝 검색 : 검색어(" )
				String filename = subStr.substring(0, quotMarkPos);
				
				log.info("이미지 파일명 : " + filename);
				
				count++; // 이미지 추출되었으므로 카운터 증가
				
				imgList.add(filename); // 리스트에 추가
				
				log.info("----------------------------------------");
			
			} //  while
		
		} // if

		return imgList;
	}

}
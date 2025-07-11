package com.javateam.springFileuploadDragDrop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.javateam.springFileuploadDragDrop.service.FileNamingEnDecodingUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileUploadController {

	@Autowired
	FileNamingEnDecodingUtil fnedUtil;

	@Value("${fileUpload.path}")
	// application.properties 파일의 imageUpload.path=E:/KSZ/upload/
	private String fileUploadPath;


	@PostMapping(value = "/fileUpload/post")
	public ResponseEntity<Map<String, Object>> upload(MultipartHttpServletRequest multipartRequest) {

		log.info("##### /fileUpload/post ######");
		
		String msg = ""; // 파일 업로드 이후 메시지
		
		Map<String, Object> map = new HashMap<>();
		
		/////////////////////////////////////////////////////////////////////////////////
		
		log.info("multipartRequest.getFileMap().size() 파일 수 : " + multipartRequest.getFileMap().size());
		
//		Iterator<String> itr = multipartRequest.getFileNames();
//
//		while (itr.hasNext()) {
//
//			MultipartFile mpf = multipartRequest.getFile(itr.next());
//
//			String originalFilename = mpf.getOriginalFilename(); // 파일명
//
//			// 원래 파일명 암호화
//			String encodedFileName = fnedUtil.encodeFilename(originalFilename);
//
//			// String fileFullPath = fileUploadPath + "/" + encodedFileName; // 파일 전체 경로
//			String fileFullPath = fileUploadPath + encodedFileName; // 파일 전체 경로
//
//			try {
//				
//				mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리
//
//				log.info("originalFilename : " + originalFilename);
//				log.info("encodedFileName : " + encodedFileName);
//				log.info("fileFullPath : " + fileFullPath);
//				
//				msg = "파일 업로드에 성공하였습니다.";
//				map.put("encodedFileName", encodedFileName);
//				map.put("originalFilename", originalFilename);
//				
//			} catch (Exception e) {
//				log.error("파일 업로드 에러 : " + fileFullPath);
//				e.printStackTrace();
//				
//				msg = "파일 업로드에 실패하였습니다.";
//			} // try
//
//		} // while
		
//		Iterator<String> itr = multipartRequest.getFileNames();
//
//		while (itr.hasNext()) {

		MultipartFile mpf = multipartRequest.getFile("file");

		String originalFilename = mpf.getOriginalFilename(); // 파일명

		// 원래 파일명 암호화
		String encodedFileName = fnedUtil.encodeFilename(originalFilename);

		// String fileFullPath = fileUploadPath + "/" + encodedFileName; // 파일 전체 경로
		String fileFullPath = fileUploadPath + encodedFileName; // 파일 전체 경로

		try {
			
			mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리

			log.info("originalFilename : " + originalFilename);
			log.info("encodedFileName : " + encodedFileName);
			log.info("fileFullPath : " + fileFullPath);
			
			msg = "파일 업로드에 성공하였습니다.";
			map.put("encodedFileName", encodedFileName);
			map.put("originalFilename", originalFilename);
			
		} catch (Exception e) {
			log.error("파일 업로드 에러 : " + fileFullPath);
			e.printStackTrace();
			
			msg = "파일 업로드에 실패하였습니다.";
		} // try

		////////////////////////////////////////////////////////////////////////////
		
		map.put("msg", msg);
				
		return ResponseEntity.ok(map);
	} //
	
	@PostMapping(value = "/fileUpload/postMulti")
	public ResponseEntity<List<Map<String, Object>>> uploadMulti(MultipartHttpServletRequest multipartRequest) {

		log.info("##### /fileUpload/post ######");
		
		String msg = ""; // 파일 업로드 이후 메시지
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		
		/////////////////////////////////////////////////////////////////////////////////
		
		log.info("multipartRequest.getFileMap().size() 파일 수 : " + multipartRequest.getFileMap().size());
		
		Iterator<String> itr = multipartRequest.getFileNames();

		while (itr.hasNext()) {
			
			map = new HashMap<>();

			MultipartFile mpf = multipartRequest.getFile(itr.next());

			String originalFilename = mpf.getOriginalFilename(); // 파일명

			// 원래 파일명 암호화
			String encodedFileName = fnedUtil.encodeFilename(originalFilename);

			// String fileFullPath = fileUploadPath + "/" + encodedFileName; // 파일 전체 경로
			String fileFullPath = fileUploadPath + encodedFileName; // 파일 전체 경로

			try {
				
				mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리

				log.info("originalFilename : " + originalFilename);
				log.info("encodedFileName : " + encodedFileName);
				log.info("fileFullPath : " + fileFullPath);
				
				msg = "파일 업로드에 성공하였습니다.";
				map.put("encodedFileName", encodedFileName);
				map.put("originalFilename", originalFilename);
				
				list.add(map);
				
			} catch (Exception e) {
				log.error("파일 업로드 에러 : " + fileFullPath);
				e.printStackTrace();
				
				msg = "파일 업로드에 실패하였습니다.";
			} // try
			
			map.put("msg", msg);

		} // while

		// return ResponseEntity.ok(map);
		return ResponseEntity.ok(list);
	} //
	
}
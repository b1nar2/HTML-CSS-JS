package com.javateam.springBootFileUpload.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.service.BoardService;
import com.javateam.springBootFileUpload.service.FileNamingEnDecodingUtil;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReactFileUploadRestController {
	
	@Value("${imageUpload.path}")
	private String uploadPath; 
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private FileNamingEnDecodingUtil fileNamingEnDecodingUtil;
	
	@Autowired
	private BoardService boardService;
	
	// CORS : https://developer.mozilla.org/ko/docs/Web/HTTP/CORS
	@CrossOrigin(origins = "http://localhost:5173")	
	// @CrossOrigin(origins = "*")
	@PostMapping("/api/reactBoardImageUpload")
	public ResponseEntity<String> reactBoardImageUpload(@RequestParam("image") MultipartFile multipartFile) {
		
		ResponseEntity<String> responseEntity = null;		
		String result = null;
		
	    if (multipartFile.isEmpty()) {
	    	
	        // return ResponseEntity.badRequest().body("전송 이미지 파일이 비어있습니다."); // 400
	    	return new ResponseEntity<>("전송 이미지 파일이 비어있습니다.", HttpStatus.BAD_REQUEST); // 400
	    } //

		try {
			
			try {
				
				String encFileName = fileNamingEnDecodingUtil.encodeFilename(multipartFile.getOriginalFilename());
				String savePath = uploadPath + encFileName;			
				log.info("업로드 파일경로/파일명 : {}", savePath);				
				
				File fileSave = new File(savePath); 
				
				if(!fileSave.exists()) { 
					fileSave.mkdirs();
				}
                
				multipartFile.transferTo(fileSave); // fileSave의 형태로 파일 저장
				
				// 클라이언트(react)에서 게시판 컨텐츠 부분에 보여줄 삽입(업로드) 이미지 주소
				result = "http://localhost:8181" + application.getContextPath() 
					   + "/image/" + encFileName;
				
				responseEntity = new ResponseEntity<>(result, HttpStatus.OK); // 정상
				
			} catch (IOException e) {
				
				log.error("파일 업로드 에러 : " + e);
				responseEntity = new ResponseEntity<>(result, HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
			} 
			
		} catch (Exception e) {
			
			log.error("reactBoardImageUpload rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}
		
		return responseEntity;
	} //
	
	// 추가 인자 : 게시글 제목 
	// CORS : https://developer.mozilla.org/ko/docs/Web/HTTP/CORS
	@CrossOrigin(origins = "http://localhost:5173")
	// @CrossOrigin(origins = "*")
	@PostMapping("/api/reactBoardSubmitProc")
	public ResponseEntity<String> reactBoardSubmitProc(@RequestParam("board_title") String boardTitle,
													   @RequestParam("board_writer") String boardWriter,
													   @RequestParam("board_content") String boardContent) {
		
		ResponseEntity<String> responseEntity = null;
		
		try {
			
			if (boardContent.trim().equals("")) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
				
			} else {
				
				log.info("boardContent : " + boardContent);
				
				// DB에 저장 처리
				BoardEntity boardEntity = BoardEntity.builder()
													 .boardTitle(boardTitle) // 게시글 제목 추가 
													 .boardContent(boardContent)
													 .boardWriter(boardWriter)
													 .boardDate(new Date(System.currentTimeMillis()))
													 .build();
				
				long boardId = boardService.writeBoard(boardEntity);
				
				responseEntity = new ResponseEntity<>(boardId + "", HttpStatus.OK); 
				
			} // 
			
		} catch (Exception e) {
			
			log.error("reactBoardSubmitProc rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}
		
		return responseEntity;
	} //
	
	@CrossOrigin(origins = "http://localhost:5173")	
	// @CrossOrigin(origins = "*")
	@GetMapping("/api/reactBoardReadProc")
	public ResponseEntity<BoardEntity> reactBoardSubmitProc(@RequestParam("board_id") Long boardId) {
		
		ResponseEntity<BoardEntity> responseEntity = null;		
		
		try {
			
			if (boardService.IsBoardId(boardId) == false) { // 해당 게시글 아이디가 없으면...
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
				
			} else {
				
				log.info("boardId : " + boardId);
				
				responseEntity = new ResponseEntity<>(boardService.readBoard(boardId), HttpStatus.OK); 
				
			} // 
			
		} catch (Exception e) {
			
			log.error("reactBoardReadProc rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}
		
		return responseEntity;
	} //

} //
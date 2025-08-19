package com.javateam.springBootFileUpload.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReactUpdateRestController {
	
	@Value("${imageUpload.path}")
	private String uploadPath; 
		
	@Autowired
	private BoardService boardService;
	
	// 추가 : 게시글 제목
	@CrossOrigin(origins = "http://localhost:5173")	
	// @CrossOrigin(origins = "*")
	@PostMapping("/api/reactBoardUpdateSubmitProc")
	public ResponseEntity<String> reactBoardUpdateSubmitProc(@RequestParam("board_id") Long boardId,
															 @RequestParam("board_writer") String boardWriter,
															 @RequestParam("board_title") String boardTitle,
															 @RequestParam("board_content") String boardContent) {
		
		ResponseEntity<String> responseEntity = null;		
		
		try {
			
			if (boardContent.trim().equals("")) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
				
			} else {
				
				log.info("boardContent : " + boardContent);
				
				// DB에 저장 처리
				BoardEntity boardEntity = BoardEntity.builder()
													 .boardId(boardId)
													 .boardContent(boardContent)
													 .boardTitle(boardTitle) // 추가 => 게시글 제목
													 .boardWriter(boardWriter)
													 .boardDate(new Date(System.currentTimeMillis())) // 게시글 수정일
													 .build();
				
				boardService.updateBoard(boardEntity);
				
				responseEntity = new ResponseEntity<>(boardId + "", HttpStatus.OK); 
				
			} // 
			
		} catch (Exception e) {
			
			log.error("reactBoardSubmitProc rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}
		
		return responseEntity;
	} //
	
} //
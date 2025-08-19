package com.javateam.springBootFileUpload.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.domain.PageDTO;
import com.javateam.springBootFileUpload.repository.BoardRepository;
import com.javateam.springBootFileUpload.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReactListRestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private BoardService boardService; 
	
	// @CrossOrigin("http://localhost:5173")
	@CrossOrigin("*")
	@GetMapping("/api/reactBoardPagingProc")
	public ResponseEntity<Map<String, Object>> reactBoardPagingProc(@RequestParam(value="page", defaultValue = "1") int page,
								@RequestParam(value="boardPerPage", defaultValue = "10") int boardPerPage) {
		
		// ResponseEntity<List<BoardEntity>> responseEntity = null;
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> resultMap = new HashMap<>();
				
		PageDTO pageDTO = new PageDTO();
		
		try {
			
			// 마지막 페이지
			int lastPage = boardRepo.findAll(PageRequest.of(0, 10)).getTotalPages();
			
			log.info("lastPage : " + lastPage);
			
			if (page < 1 || page > lastPage) {
				
				responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT); // 컨텐츠가 없을 때
				
			} else {
				
				List<BoardEntity> list = boardService.getListByPage(page, boardPerPage);
				
				list.forEach(x -> {
					log.info("게시글 : "+ x);
				});
				
				// 페이징 변수들
				// 총 페이지 수
		   		// int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리
				// int maxPage = PageDTO.getMaxPage(int listCount, int limit)
				int maxPage = lastPage;
				
				// 현재 페이지에 보여줄 시작 페이지 수
		   		// int startPage = (((int) ((double)currPage / 10 + 0.9)) - 1) * 10 + 1;
				int startPage = PageDTO.getStartPage(page, boardPerPage);
				
				// 현재 페이지에 보여줄 마지막 페이지 수
				 int endPage = maxPage;
		   	    
		   	    // if (endPage > maxPage) endPage = maxPage;
		   	    
		   	    pageDTO = PageDTO.builder()
		   	    		         .startPage(startPage) 
   	    					     .endPage(endPage)		   	    					    
   	    					     .previousPage(page-1 < 1 ? 1 : page-1)
   	    					     .nextPage(page+1 > endPage ? endPage : page+1)
   	    					     .currentPage(page)
   	    					     .boardPerPage(boardPerPage)
   	    					     .build();
		   	    
		   	    resultMap.put("boardList", list);
		   	    resultMap.put("pageDTO", pageDTO);
		   	    
		   	    responseEntity = new ResponseEntity<>(resultMap, HttpStatus.OK);
			} // 
			
		} catch (Exception e) {
			
			log.error("reactBoardPagingProc rest controller 에러 : {}", e);
			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 내부 서버 에러의 경우
		}
		
		return responseEntity;
	}

}
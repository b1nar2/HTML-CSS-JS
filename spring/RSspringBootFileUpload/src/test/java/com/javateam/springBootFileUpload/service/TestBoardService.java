package com.javateam.springBootFileUpload.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestBoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private BoardService boardService;

	@Test
	void testFirstPage() {

		List<BoardEntity> list = boardService.getListByPage(1, 10);
		
		log.info("첫번째 레코드 : " + list.get(0));
		log.info("마지막 레코드 : " + list.get(9));
		
		assertEquals(8, list.get(0).getBoardId());
		assertEquals(300, list.get(9).getBoardId());
	} //
	
	@Test
	void testLastPage() {
		
		// int lastPage = (int)Math.floor(boardRepo.count() / 10  + 0.95);
		int lastPage = boardRepo.findAll(PageRequest.of(0, 10)).getTotalPages();
		
		log.info("마지막 페이지 : " + lastPage);

		List<BoardEntity> list = boardService.getListByPage(lastPage, 10);
		
		int firstRow = 0;
		int lastRow = list.size() == 1 ? firstRow : list.size() - 1;  
				
		log.info("첫번째 레코드 : " + list.get(firstRow));
		log.info("마지막 레코드 : " + list.get(lastRow));
		
		assertEquals(280, list.get(firstRow).getBoardId());
		assertEquals(289, list.get(lastRow).getBoardId());
	} //

}

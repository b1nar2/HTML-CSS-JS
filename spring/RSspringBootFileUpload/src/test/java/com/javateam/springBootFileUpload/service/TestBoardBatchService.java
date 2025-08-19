package com.javateam.springBootFileUpload.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestBoardBatchService {
	
	@Autowired
	private BoardBatchService bbs;

	@Test
	void testBoardImageAutoBatchDeletion() {
	
		bbs.boardImageAutoBatchDeletion();
	}

}
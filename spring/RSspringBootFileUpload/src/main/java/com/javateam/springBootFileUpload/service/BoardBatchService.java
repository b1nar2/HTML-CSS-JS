package com.javateam.springBootFileUpload.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.javateam.springBootFileUpload.domain.BoardImgEntity;
import com.javateam.springBootFileUpload.repository.BoardImgRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardBatchService {
	
	private final BoardImgRepository boardImgRepo;
	
	@Value("${imageUpload.path}")
	private String imgRepoPath;
	
	// 매일 1분 마다 작업 실행
	// @Scheduled(cron = "0 0/1 * * * ?")
	
	// 서버 트래픽 최저 시간대 : 새벽 2 ~ 5시
	// 매일 새벽 3시에 가베지 삭제 일괄(배치) 작업
	// @Scheduled(cron = "0 0 3 * * ?")
	
	// 매일 오후 5시 23분에 일괄 작업
	@Scheduled(cron = "0 23 5 * * ?")
	public void boardImageAutoBatchDeletion() {
		
		log.info("게시판 가베지 이미지 일괄(batch) 삭제 작업");
		
		// 게시판의 이미지 테이블의 현황과 이미지 저장소 현황 일치시키는 작업
		// : 가베지(garbage) 이미지 파일 자동 소거(삭제)
		List<BoardImgEntity> boardImgEntities = (List<BoardImgEntity>) boardImgRepo.findAll();
		
		// 게시글 삽입 이미지 테이블(BOARD_IMG_TBL)에 존재하는 이미지 파일들
		List<String> boardImgs = boardImgEntities.stream().map(x -> x.getBoardImg()).toList();
		
		Path path = Paths.get(imgRepoPath);
		
		Stream<Path> pathStream = null;

		try {
			
			pathStream = Files.list(path);
			
			pathStream.forEach((p) -> {
				
				if (Files.isRegularFile(p) == true) { // 정상 파일이면...
					
					Path storedFilePath = p.getFileName();
					
					// log.info("저장소에 존재하는 파일 : " + storedFilePath);
					
					// 삭제할 가베지 이미지 파일들
					if (boardImgs.contains(storedFilePath.toString()) == false) {
						
						log.info("삭제할 가베지 이미지 파일 :" + storedFilePath);
						
						// 삭제
						try {
							Files.deleteIfExists(p);
						} catch (IOException e) {
							log.error("가베지 이미지 파일 삭제 실패 : " + e);
							e.printStackTrace();
						}
					} //
					
				} //
				
			});

		} catch (IOException e) {
			log.error("boardImageAutoBatchDeletion error : " + e);
			e.printStackTrace();
		} //		
		
	} //

}
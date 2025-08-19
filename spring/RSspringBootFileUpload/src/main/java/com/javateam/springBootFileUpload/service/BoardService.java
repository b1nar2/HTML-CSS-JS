package com.javateam.springBootFileUpload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.springBootFileUpload.domain.BoardEntity;
import com.javateam.springBootFileUpload.domain.BoardImgEntity;
import com.javateam.springBootFileUpload.repository.BoardImgRepository;
import com.javateam.springBootFileUpload.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	
	@Value("${imageUpload.path}")
	private String uploadPath; 
	
	private final BoardRepository boardRepo;
	
	private final BoardImgRepository boardImgRepo;
	
	private final ImageService imageService;
		
	@Transactional(rollbackFor = Exception.class)
	public long writeBoard(BoardEntity boardEntity) {
	
		boardEntity = boardRepo.save(boardEntity);
		
		List<String> images 
			= imageService.getImageList(boardEntity.getBoardContent(), 
										"/springBootFileUpload/image/");
				
		BoardImgEntity boardImgEntity = null;
		
		for (String image : images) {

			boardImgEntity = BoardImgEntity.builder()
										  .boardId(boardEntity.getBoardId())
										  .boardImg(image)
										  .build();
			boardImgRepo.save(boardImgEntity);			
		} // for
		
		return boardEntity.getBoardId();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateBoard(BoardEntity boardEntity) {
	
		boardEntity = boardRepo.save(boardEntity);
				
		// 기존 이미지 테이블의 삽입 이미지들 조회
		List<String> defaultImages = boardImgRepo.findAllByBoardId(boardEntity.getBoardId())
									  			 .stream()
									  			 .map(x -> x.getBoardImg()).toList();
		// 수정 전송된 이미지들
		List<String> updateImages = imageService.getImageList(boardEntity.getBoardContent(), 
															 "/springBootFileUpload/image/");
		
		// 수정된 내용에 이미지가 없거나 수량이 줄었을 때 조치
		// ex) 기존 DB 저장 이미지 : A.jpg, B.jpg
		//           수정된 이미지 : A.jpg, C.jpg => B.jpg => DB 및 파일 저장소에서 삭제 조치
		
		// 에러 패치 위한 변수 재할당)
		// 에러) Type Local variable updateImages defined in an enclosing scope must be final or effectively final
		List<String> uImgs = updateImages;
		
		// 삭제할 이미지들 추출
		List<String> deleteImages = defaultImages.stream()
												 .filter(x -> !uImgs.contains(x))
												 .toList();
		
		for (String deleteImage : deleteImages) {
			
			// 파일 DB table에서 이미지 삭제
			boardImgRepo.deleteByBoardIdAndBoardImg(boardEntity.getBoardId(), deleteImage);
			
			// 파일 저장소에서 이미지 삭제
			try {
				Files.deleteIfExists(Paths.get(uploadPath + deleteImage));
			} catch (IOException e) {
				log.error("가베지 이미지 파일 삭제 실패 : " + e);
				e.printStackTrace();
			}
			
		}				
		
		// 기존 삽입 이미지들은 저장에서 제외 : 이중 저장 방지		
		updateImages = updateImages.stream()
								   .filter(x -> !defaultImages.contains(x))
								   .toList();
							
		BoardImgEntity boardImgEntity = null;
		
		for (String updateImage : updateImages) {

			boardImgEntity = BoardImgEntity.builder()
										  .boardId(boardEntity.getBoardId())
										  .boardImg(updateImage)
										  .build();
			
			boardImgRepo.save(boardImgEntity);	
		} // for
		
	}
	
	@Transactional(readOnly=true)
	public BoardEntity readBoard(Long boardId) {
	
		return boardRepo.findById(boardId).get();
	}
	
	@Transactional(readOnly=true)
	public boolean IsBoardId(Long boardId) {
	
		return boardRepo.countByBoardId(boardId) == 1 ? true : false;
	}

	@Transactional(readOnly=true)
	public List<BoardEntity> getListByPage(int page, int boardPerPage) {
		
		List<BoardEntity> boardList = new ArrayList<>();
		
		Pageable pageable = PageRequest.of(page - 1, boardPerPage); 
		boardList = boardRepo.findAll(pageable).getContent();
		
		return boardList;
	}
	
}
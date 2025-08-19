package com.javateam.springBootFileUpload.repository;

 import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.javateam.springBootFileUpload.domain.BoardEntity;

// public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
public interface BoardRepository 
				extends CrudRepository<BoardEntity, Long>, 
						ListPagingAndSortingRepository<BoardEntity, Long> { // 변경 : 다중 상속
	
	int countByBoardId(Long boardId);
	
}
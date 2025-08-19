package com.javateam.springBootFileUpload.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javateam.springBootFileUpload.domain.BoardImgEntity;

public interface BoardImgRepository extends CrudRepository<BoardImgEntity, Long> {

	public List<BoardImgEntity> findAllByBoardId(Long boardId);
	
	public void deleteByBoardIdAndBoardImg(Long boardId, String boardImg);

}
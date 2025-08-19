package com.javateam.springBootFileUpload.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO {
	
	/** 시작 페이지 */
	private int startPage;
	
	/** 이전 페이지 */
	private int previousPage;
	
	/** 현재 페이지 */
	private int currentPage;
	
	/** 다음 페이지 */
	private int nextPage;
	
	/** 마지막(끝) 페이지 */
	private int endPage;
	
	/** 페이지당 게시글 수 */
	private int boardPerPage;
	
	// 마지막 페이지 계산
	public static int getMaxPage(int listCount, int limit) {
		return (int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리
	}
	
	// 첫 페이지 계산
	public static int getStartPage(int currPage, int limit) {
		return  (((int) ((double)currPage / limit + 0.9)) - 1) * limit + 1;
	}

}

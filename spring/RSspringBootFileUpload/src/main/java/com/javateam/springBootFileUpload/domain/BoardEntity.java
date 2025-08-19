package com.javateam.springBootFileUpload.domain;

// import java.sql.Date; 
import java.util.Date; // Tip) 년월일 이외에 "시분초"가 일일이 표시가 잘되려면 java.util.Date를 쓰는 것을 추천. 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="BOARD_TBL")
@SequenceGenerator(
	    name = "BOARD_SEQ_GENERATOR",
	    sequenceName = "BOARD_SEQ",
	    initialValue = 1,
	    allocationSize = 1)
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_SEQ_GENERATOR")
	@Column(name="BOARD_ID")
	private Long boardId;
	
	// 필드 추가 : 게시글 제목
	@Column(name="BOARD_TITLE")
	private String boardTitle;
	
	@Column(name="BOARD_WRITER")
	private String boardWriter;
	
	@Column(name="BOARD_CONTENT")
	private String boardContent;
	
	@Column(name="BOARD_DATE")
	private Date boardDate;

}

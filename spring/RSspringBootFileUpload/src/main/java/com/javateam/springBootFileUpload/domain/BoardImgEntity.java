package com.javateam.springBootFileUpload.domain;

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
@NoArgsConstructor // 추가
@AllArgsConstructor // 추가
@Builder
@Table(name="BOARD_IMG_TBL")
@SequenceGenerator(
	    name = "BOARD_IMG_SEQ_GENERATOR",
	    sequenceName = "BOARD_IMG_SEQ",
	    initialValue = 1,
	    allocationSize = 1)
public class BoardImgEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_IMG_SEQ_GENERATOR")
	@Column(name="BOARD_IMG_ID")
	private long boardImgId;
	
	@Column(name="BOARD_ID")
	private long boardId;
	
	@Column(name="BOARD_IMG")
	private String boardImg;
	
}

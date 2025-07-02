package com.javateam.springBootMVC.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TEST_TBL")
@Data
@NoArgsConstructor // 기본 생성자(파라미터 없는 기본 생성자 생성)
@AllArgsConstructor // 생성자 오버로딩(모든 필드를 인자로 받는 생성자 생성)
@Builder
public class TestEntity {

	@Id // 프라이머리 키
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	
}

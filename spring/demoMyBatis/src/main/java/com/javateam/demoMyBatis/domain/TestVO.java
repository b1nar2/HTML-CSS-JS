package com.javateam.demoMyBatis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// POJO : Plain Old Java Object
@Data
@NoArgsConstructor // 인자 없는 생성자(기본 생성자)
@AllArgsConstructor
@Builder
public class TestVO { 

		private String id;
		private String name;
		private String address;
}

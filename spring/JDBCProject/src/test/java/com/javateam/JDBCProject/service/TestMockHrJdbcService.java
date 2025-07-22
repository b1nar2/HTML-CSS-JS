package com.javateam.JDBCProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javateam.JDBCProject.dao.HrJdbcDAO;
import com.javateam.JDBCProject.vo.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TestMockHrJdbcService {
		
	@Mock
	private HrJdbcDAO hrJdbcDAO;
	
	@InjectMocks
	private HrJdbcService hrJdbcService;

	@Test
	@DisplayName("HrJdbcService.findById 단위 테스트")
	void testHrJdbcService() throws Exception {
		
		// given : 테스트 전제 조건
		EmployeesVO expectedEmployeesVO
			= EmployeesVO.builder()				   		
				   		.employeeId(100)
				   		.firstName("Steven")
				   		.lastName("King")
				   		.email("SKING")
				   		.phoneNumber("515.123.4567")
				   		.hireDate(Date.valueOf("2003-06-17"))
				   		.jobId("AD_PRES")
				   		.salary(24000)				   		
				   		.commissionPct(0)
				   		.managerId(0)
				   		.departmentId(90)
				   		.build();
		
		////////////////////////////////////////////////////////////////////////////////////
		
		// 참고) given() & when()의 차이
		
		// given() : 
		// 모의(목 : Mock) 객체의 행위(동작: behavior)를 정의하는데 사용되며, 
		// BDD(행위 주도 개발 : Behavior Driven Developement) 스타일 코드의 테스트를 위해 사용됨.  
		// "Given-When-Then"의 Given(테스트 전제 사항)에 해당. 테스트 코드의 테스트 목적 명시. 
		// 대상 메서드 호출시의 반환 예정값 설정시 사용. 
		// 테스트 코드의 의미적인 가독성이 when()보다는 given()이 더 높기 때문에 given()이 선호됨. 
		
		// when() :
		// 테스트 대상 메서드의 호출에 대한 행위(동작)을 정의함. given()과 같이 호출시 반환될 값을 설정
		// "Given-When-Then"의 Given(테스트 전제 사항) 에 해당. 
		// 메서드의 의미 그대로 When에 해당될 것 같지만 의외로 Given으로 간주됨.
		// 마찬가지로 given()과 기능은 동일하지만,
		// 테스트 코드의 의미적인 가독성이 when()보다는 given()이 더 높기 때문에 given()이 대체로 선호됨.
		
		// given(전제) : 테스트 대상 메서드에 인자값 100으로 호출시에는 
		// expectedEmployeesVO(목 DAO 및 목 Service의 결과로 나올 실제값) 라는 VO를 리턴할 것이다. => 정상 처리
		
		// 중요) mockResultVO에 설정된 값은 아래의 서비스단 테스트,
		// 즉 "EmployeesVO actualEmployeesVO = hrJdbcService.findById(100);"
		// 에서 리턴될 결과를 "테스트용 모의(Mock, 가짜) 결과"로 나올 수 있도록 미리 설정하는 것이다.
		
		EmployeesVO mockResultVO 
			= EmployeesVO.builder()				   		
						.employeeId(100)
						.firstName("Steven")
						.lastName("King")
						.email("SKING")
						.phoneNumber("515.123.4567")
						.hireDate(Date.valueOf("2003-06-17"))
						.jobId("AD_PRES")
						.salary(24000)				   		
						.commissionPct(0)
						.managerId(0)
						.departmentId(90)
								.build();
		
		given(hrJdbcDAO.findById(100)).willReturn(mockResultVO);
		
		// given(전제) : 테스트 대상 메서드에 인자값 100으로 호출시는 null을 리턴할 것이다. => 정상이라면 에러 처리
		// given(hrJdbcService.findById(100)).willReturn(null);
		
		// given(전제) : 테스트 대상 메서드에 인자값 10000(존재하지 않는 값)으로 호출시는 null을 리턴할 것이다. => 정상이라면 에러 처리
		// given(hrJdbcService.findById(10000)).willReturn(null);
		
		// when
		// 테스트 대상 메서드에 인자값 100으로 호출시는 expectedEmployeesVO 라는 VO를 리턴한다 => 정상 처리
		// when(hrJdbcService.findById(100)).thenReturn(expectedEmployeesVO);
		
		// 테스트 대상 메서드에 인자값 100으로 호출시는 null을 리턴한다 => 정상이라면 에러 처리
		// when(hrJdbcService.findById(100)).thenReturn(null);
		
		// 테스트 대상 메서드에 인자값 10000(존재하지 않는 값)으로 호출시는 null을 리턴한다 => 정상 처리
		// when(hrJdbcService.findById(100000)).thenReturn(null);
		
		//////////////////////////////////////////////////////////////////////////////
				
		// when : 테스트 목적을 보여주는 단계 : 테스트 대상 및 결과값 도출
		EmployeesVO actualEmployeesVO = hrJdbcService.findById(100);
		
		log.info("expectedEmployeesVO : " + expectedEmployeesVO);
		log.info("actualEmployeesVO : " + actualEmployeesVO);

		//////////////////////////////////////////////////////////////////////////////
		
		// then : 테스트 결과 검증(verification)
				
		// JUnit식의 검증 : 예상값/실제값 동등비교 판정(assert) 검증
		assertEquals(expectedEmployeesVO, actualEmployeesVO);
		
		// verify : Mockito식의 검증
		// 테스트 대상 메서드가 인자값 100으로 1번 호출된 적이 있는지 검증 => 정상 처리		
		// verify(hrJdbcService, times(1)).findById(100);
		
		// 테스트 대상 메서드가 인자값 100으로 최소 1번은 호출된 적이 있는지 검증 => 정상 처리
		verify(hrJdbcDAO, atLeastOnce()).findById(100);	
		
		// 테스트 대상 메서드가 인자값 100으로 호출된 적이 (전혀) 없는지 검증 => 정상적이라면 에러 발생
		// verify(hrJdbcService, never()).findById(100);		
	}

}

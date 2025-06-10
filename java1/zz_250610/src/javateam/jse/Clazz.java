package javateam.jse;

public class Clazz {

	// 멤버 변수(필드:field)
	// 묵시적 초기값 가짐
	String name; // has-A: 집합(aggregation)관계
	
	// (기본 생성자 외의) 다른 생성자가 있을 경우, 기본 생성자 호출 => 명시적 정의가 수반되어야 함!
	Clazz() {
		System.out.println("기본 생성자"); // 기본 생성자 => 인자X
	}
	
	// 오버로딩 생성자
	Clazz(String name) {
		System.out.println("오버로딩 생성자");
		this.name = name;
	}
	
	void method(String name) {
//		System.out.println("인스턴스 메서드 : " + name);
		this.name = name; // 멤버 필드 <== 인자
		System.out.println("인스턴스 메서드 : " + this.name);
	}
	
	// 메서드 오버로딩(over-loading)
	// 같은 이름의 메서드에 인자의 자료형 종류, 순서, 갯수 변화
	void method(String name, int age) {
		System.out.printf("%s의 나이는 %d살 입니다.%n", name, age);
	}
	
	
	// String ... foods : 가변인자 => 배열 처리. 오버로딩시 마지막 인자!
	void method(String name, int age, String ... foods) {
		
		String str ="";

		str += name + "는 ";
		str += age + "살이고, ";
		
		for (String food : foods) {
			str += food + " ";
		}
		System.out.println(str + "을 좋아합니다.");
	}
	
	public static void main(String[] args) {

		// 인스턴스(객체 변수) 생성 과정
		// Clazz : 참조 자료형(클래스)
		// clazz : 지역 변수
		// new : 객체(인스턴스) 생성 연산자
		// Clazz() : 생성자(constructor, 구축자), 클래스와 동일한 명칭의 메서드(함수), 인스턴스 초기화 역할
		// 인자 없는(no-argumented) 생성자 => 기본(default) 생성자
		// 특정 생성자가 정의되어 있지 않은 상태 => 기본 생성자 묵시적(implicit) 정의 가능!
		// new Clazz(); => 익명 인스턴스 생성
		// clazz = new Clazz(); => clazz라는 이름의 인스턴스 생성
		
		// Clazz clazz = new Clazz();
		Clazz clazz;
		clazz = new Clazz();
		clazz = new Clazz("Java");
		
		System.out.println(clazz.name); // null
		clazz.method("Java"); // 메서드 호출(call)
		clazz.method("React, 10");
//		clazz.method("윈터", 20, "사과", "귤", "감");
		clazz.method("윈터", 20,
				new String[]{"사과", "귤", "감"});
		
		
	}

}

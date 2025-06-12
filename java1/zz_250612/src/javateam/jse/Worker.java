package javateam.jse;

public class Worker implements Director {
	
	public void workerMethod() {
		System.out.println("사원의 메서드");
	}
	
	// 선택적 오버라이딩 => 융통성!
	@Override
	public void defaultMethod() {
		Director.super.defaultMethod();
		// 인터페이스의 default 메서드
		System.out.println("부장님 일을 오버라이딩하다");
		
	}
	
	
	@Override // 애너테이션(an-notation)
	// 오버라이딩 명시/감독 !
	// 설정(configuration) 정보
	// : XML, text(~~.cfg...) => 축소 !
	public void order() {
		System.out.println("Director 지시대로 일하다");
	}

}

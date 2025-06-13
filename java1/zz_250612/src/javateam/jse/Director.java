package javateam.jse;

public interface Director {

	// 자바의 인터페이스는 정적 상수 멤버 필드만 허용!
	// int num; // (X) 일반 멤버 필드
	int num = 1; // (O)  =>  p- s- f- 생략 가능
	//public static final int num = 1; // (O)
	// private static final int num = 1; // (X)
	// static final int num = 1; // (O) default 접근 제어자가 아님 => public
	// protected static final int num = 1; // (X)
	
	// 생성자 쓸 수 없음
	// public Director() {}
	
	// Java8 이전의 인터페이스 : 추상(지시) 메서드만 존재!
	// 메서드
	// void method(); {} // (X)
	
	// since Java8
	// private void privateMethod() {} // (O)
	
	static void staticMethod() { // (O) 묵시적 public!
	// protected static void staticMethod() { // (X)
	// private static void staticMethod() { // (O)
		System.out.println("인터페이스의 정적 메서드");
	}
	
	default void defaultMethod() {
		System.out.println("default 메서드");
	}
	
	// 추상 메서드
	// abstract void order();
	void order();
	
	public static void main(String[] args) {
		System.out.println("인터페이스 실행");
		// Director director = new Director(); // (X)
		staticMethod();
	}
	
}
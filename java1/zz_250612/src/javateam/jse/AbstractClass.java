package javateam.jse;

public abstract class AbstractClass {
//abstract public class AbstractClass {

	String name;
	AbstractClass() {
		System.out.println("추상 클래스의 생성자");
	}
	
	void method() {
		System.out.println("일반 메서드");
	}
	
	// 추상 메서드
	abstract void abstractMethod();
	// void abstractMethod(); // (X)
}

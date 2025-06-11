package javateam.jse;

public class JavaEx26 {
	
	void method() {
		System.out.println("메서드");
	}
	
	static {
		System.out.println("정적 블럭-1");
	}	
	
	public static void main(String[] args) {

		System.out.println("main");
		new JavaEx26().method(); // 익명인스턴스(일회용)
	}

	static {
		System.out.println("정적 블럭-2");
	}
	
	{
		System.out.println("인스턴스 블럭: 인스턴스 생성시 실행됨");
	}

}


/* 
인스턴스 블럭은 객체가 생성되는 시점에 자동 실행되는 초기화 코드이고
메서드는 객체가 생성된 이후에 호출해야 실행되기 때문에
항상 인스턴스 블럭이 메서드보다 먼저 실행됨
 */

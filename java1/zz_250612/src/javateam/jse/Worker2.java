package javateam.jse;

public class Worker2 extends AbstractClass {

	public Worker2() {
//			super();
		System.out.println("사원의 생성자");
	}
	
	@Override
	void abstractMethod() {
		System.out.println("과장님 지시대로 일하다");
	}
	
	public static void main(String[] args) {
		
//		AbstractClass work = new AbstractClass(); // (X)
		AbstractClass work = new Worker2(); // (O)
		work.abstractMethod();
	}
}

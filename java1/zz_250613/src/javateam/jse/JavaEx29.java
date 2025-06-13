package javateam.jse;

public class JavaEx29 {

	// 중첩/내부(inner, nested) 클래스
	// 인스턴스 멤버 클래스
	// 집합관계(has-A)
	class InnerClass {
		
		String name;

		InnerClass() {
			System.out.println("InnerClass 생성자");
		}
		
		void method() {}
		
	} // InnerClass
	
	public static void main(String[] args) {

		// InnerClass inner = new InnerClass(); // (X)
		JavaEx29 outer = new JavaEx29();

		JavaEx29.InnerClass inner
//			= new JavaEx29().new InnerClass();
			= outer.new InnerClass();
		inner.method();
		
	}

}

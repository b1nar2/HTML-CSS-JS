package javateam.jse;

// public static class JavaEx30 { // (X)
public class JavaEx30 {

	// 정적 멤버 클래스
	static class StaticClass {
		
		String name;
		
		StaticClass() {
			System.out.println("정적 클래스의 생성자");
		}
		
		void method() {
			
		}
	}

	public static void main(String[] args) {

		StaticClass st = new StaticClass(); // (O)
//		JavaEx30.StaticClass st = new StaticClass(); // (O)
//		JavaEx30.StaticClass st = JavaEx30().new StaticClass(); // (X)
		st.method();
	}

}

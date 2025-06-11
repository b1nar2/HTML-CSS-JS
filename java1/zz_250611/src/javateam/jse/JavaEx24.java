package javateam.jse;

// public class JavaEx24 extends Object { // 아래와 동일 (extends Object 생략 가능)
public class JavaEx24 {

		String name;
		int age;
		
		
		JavaEx24() {
			System.out.println("기본 생성자");
		}
		
		/**
		 * @param name
		 * @param age
		 */
		public JavaEx24(String name, int age) {
			
			super(); // 부모 클래스(Object)의 기본 생성자
			
			// 변수 : 지역변수와 멤버변수가 동명일 경우 지역변수가 우선
			// String name = "";
			
			// 멤버 필드 초기화
			// name = name; // <The assignment to variable name has no effect> => 인자에 인자를 대입한 형태

			// JaveEx24 클래스의 내장 인스턴스 => this
			this.name = name; // 멤버 필드에 인자(매개변수)를 대입
			this.age = age;
		}

		// 생성자(X) 생성자는 리턴 표기가 없음
		// 인스턴트 메서드
//		void JavaEx24() {
//			System.out.println("기본 생성자 X");
//		}
		
//		JavaEx24 JavaEx24() { // 문법(X)
//			System.out.println("기본 생성자 X");
//		}	
	
		/*static*/void method(String name) {   // 11111)
			System.out.println("name = " + name);
		}
		
		void method2() {
			// this.method("Java");
			// method("Java"); // 메서드가 다른 메서드 호출
		}
		
		static void method3() {
			// this.method("Java");
			// 인스턴스 메서드 호출시 => static의 경우 인스턴스 생성 접근
			JavaEx24 obj = new JavaEx24();
			obj.method("Java"); // 메서드가 다른 메서드 호출
			// JavaEx24.method2();
		}
		
	
	public static void main(String[] args) {
				
			JavaEx24 obj = new JavaEx24();
			// obj.JavaEx24();
			
			obj.method("Java");
			// method("java"); // (X) 11111) static void method(String ~~~ (정적 메서드) 이면 호출 가능
			
//			obj.name = "HTML";
//			System.out.println(obj.name);
//			
//			obj = new JavaEx24("Java" , 30);// obj 재할당
//			System.out.println(obj.name);
			
			JavaEx24 obj2 = new JavaEx24("Java" , 30);
			System.out.println(obj2.name);
			
	} //

} // class


			/*
			함수(function) : 기능을 가진 서브 프로그램
			메서드 : 객체 소속의 삼수
			
			자판기 = 함수
			자판기 이름 =함수명
			투입구(동전, 지폐, 카드) = 매개변수(인자)
			결과물 배출구 = 리턴(반환값)
			기계몸통 = 함수의 내부(body: {코드부})			
			*/


			/*
			 <재할당 / 오버로딩 / 오버라이딩 차이>
			 
			 재할당(:값 변경)
			 - 변수나 참조에 다른 값을 "다시 할당"하는 것.
			 - 객체, 기본형 모두 해당됨.
			 	=> 변수에 새로운 값을 다시 저장. [변수 ← 새 값]
	
			 
			 오버로딩(:상속 불필요)
			 - 같은 이름의 메서드나 생성자를 매개변수의 개수, 타입, 순서를 다르게 하여 여러 개 정의하는 것.
			 - 메서드 이름은 같지만 매개변수로 구별됨.
			 	=> 메서드 이름은 같지만 매개변수 다르게 정의. [메서드 여러 개 정의]
			 
			 -오버라이딩(:상속 필요)
			 - 상속 관계에서, 부모 클래스의 메서드를 자식 클래스가 재정의(덮어쓰기) 하는 것.
			 - 메서드 이름, 매개변수, 리턴 타입 모두 동일해야 함.
			 	=> 부모 메서드를 자식이 같은 이름으로 재정의. [상속, 다형성]
			 */


package javateam.jse;

public class JavaEx22 {

	// 멤버 변수(필드)
	// static은 멤버 필드/메서드에서만 사용 가능
	static final float PI = 3.141592F; 

	public static void main(String[] args) {

//		final float PI = 3.141592F; // final: js의 const(상수)와 유사함

//		static final float PI = 3.141592F; // static => 지역 변수에 사용 불가

		System.out.println(PI);

//		const float PI = 3.141592F; // (X) java에서 const는 예약어로만 존재하고 실제로는 사용 불가함
//		PI = 3.14F; // (X) javascript : const
		
	}// main

}

package javateam.jse;

public class JavaEx23 {
	
	public static void main(String[] args) {

//		System.out.println(Season.Spring); // 상수의 이름이 출력 => Spring
//		System.out.println(Season.Spring.name()); // 선언된 식별자 문자열 반환 => Spring
		System.out.println(Season.Spring.getSeason()); // 커스텀(한글) 설명값 => 봄
		System.out.println(Season.Spring.ordinal()); // 선언된 순서 => 0 (Spring: 0, Summer: 1, Fall: 2, Winter: 3)
		
	}

}

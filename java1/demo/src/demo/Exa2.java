package demo;

public class Exa2 {

	public static void main(String[] args) {

		// 객체 생성
		Exa1 myCar = new Exa1();
		
		// 객체 필드값 읽기
		System.out.println("제작회사 : " + myCar.company);
		System.out.println("모델명 : " + myCar.model);
		System.out.println("색상 : " + myCar.color);
		System.out.println("최고속도 : " + myCar.maxSpeed);
		System.out.println("현재속도 : " + myCar.speed);
		
		// 객체 필드값 변경
		myCar.speed = 80;
		System.out.println("변경된 속도 : " + myCar.speed);
	}

}

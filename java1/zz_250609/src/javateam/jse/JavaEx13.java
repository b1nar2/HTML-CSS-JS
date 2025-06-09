package javateam.jse;

public class JavaEx13 {

	public static void main(String[] args) {

		// <for문 - 실수의 합계>
		float sum = 0;
		
		for (float i = 1; i <= 10f; i += 1.5f) {
			
			sum += i;
			System.out.printf("1 ~ %.1f까지의 합계 = %.1f%n", i, sum);
		}// for
	}

}

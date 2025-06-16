package javateam.jse;

public class JavaEx41 {

	// 소수(prime number) 여부 판단
	static boolean isPrime(int num) {
		
		boolean result = true; // 주의! (주로 false로 사용하지만 소수 여부를 판단할 땐 true로 작성하는 것이 좋음.)
		
		for (int i = 2; i <= (int)Math.sqrt(num); i++) {
			
			if(num % i == 0) {
				result = false;
				break;
			} else {
				result = true;    // 기본값이 ture이므로 else~ 구문 삭제 가능 => 최적화
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {

//		System.out.println(Math.sqrt(9));
		System.out.println(isPrime(3));
		
		int sum = 0;
		
		for (int i = 1; i<= 100; i++) {
			
			if(i > 1 && isPrime(i)) {
//			if(i > 1 && isPrime(i) == true) {
				sum += i;
				System.out.println(i + " ");
			}
			
		} // for
		System.out.println("****************************");
		System.out.println("1 ~ 100까지의 소수의 합계 : " + sum);
		
	}

}

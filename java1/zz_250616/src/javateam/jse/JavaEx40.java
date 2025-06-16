package javateam.jse;

public class JavaEx40 {
	
	static float round(float num, int n) {
		return (float)(Math.round(num* Math.pow(10, n-1))/Math.pow(10,n-1));
	} // static + round : 숫자를 표현할 땐 값이 일정한 게 좋음

	public static void main(String[] args) {

		// 소숫점 n째자리에서 반올림
		float num = 82.55555f;
//		System.out.println(Math.round(num));
		
		// 소숫점 3째자리에서 반올림
		float num2 = (float)(Math.round(num* Math.pow(10, 2))/Math.pow(10,2));
		
		System.out.println(num2);
		
		
	}

}

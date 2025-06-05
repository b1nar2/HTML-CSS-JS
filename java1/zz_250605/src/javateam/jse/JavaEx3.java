package javateam.jse;

public class JavaEx3 {

	public static void main(String[] args) {

		// int num1 = '\uac00';
		// int num1 = '\uAC00'; // '가' : 44032
		// num1 = 1L; 		// (X) int <= long 
		// num1 = (int)1L;  // (O) int <= (int)long
		//System.out.println("num1 : " + num1); //
		
		
		// MAX_VALUE , MIN_VALUE => 최댓값, 최솟값 출력
		
		System.out.println("byte의 최댓값 : " + Byte.MAX_VALUE); // 127
		System.out.println("byte의 최솟값 : " + Byte.MIN_VALUE); // -128
		
		System.out.println("short의 최댓값 : " + Short.MAX_VALUE); // 32767
		System.out.println("short의 최솟값 : " + Short.MIN_VALUE); // -32768
		
		// 주의)
		System.out.println("char의 최댓값 : " + (int)Character.MAX_VALUE); // 65535 int로 형변환 시켜줘야 출력됨
		System.out.println("char의 최솟값 : " + (int)Character.MIN_VALUE); // 0
		
		System.out.println("int의 최댓값 : " + Integer.MAX_VALUE); // 2147483647 (16진수 표기: 0x7fffffff)
		System.out.println("int의 최솟값 : " + Integer.MIN_VALUE); // -2147483648 (16진수 표기: 0x80000000)
		
		System.out.println("long의 최댓값 : " + Long.MAX_VALUE); // 9223372036854775807
		System.out.println("long의 최솟값 : " + Long.MIN_VALUE); // -9223372036854775808

		System.out.println("double의 최댓값 : " + Double.MAX_VALUE); // 1.7976931348623157E308 
		System.out.println("double의 최솟값 : " + Double.MIN_VALUE); // 4.9E-324

		
		System.out.println("float의 최댓값 : " + Float.MAX_VALUE); // 3.4028235E38
		System.out.println("float의 최솟값 : " + Float.MIN_VALUE); // 1.4E-45
		
		// E(지수) : exponential
		float num2 = (float)1E1; //  10.0F
		// num2 = 10.0; // (X)  float(4byte 실수) <= double(8byte 실수)
		// num2 = (float)10.0;
		num2 = 10.0f;
		// num2 = 1E2F; // 100.0F
		// num2 = 1e2f; // 100.0F
		// num2 = 1e3f; // 1000.0F => 10의 3승(실수)
		// num2 = 2e3f; // 2000.0F => 10의 3승(실수)
		
		// num2 = 2E-3f; // 0.002F( "-" : 분수)
		num2 = 2e-3f; // 0.002F(분수)
		
		System.out.println("num2 : " + num2);
		
		
	}

}

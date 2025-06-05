package javateam.jse;

public class JavaEx2 {

	public static void main(String[] args) {
		
		// byte num1 = 1; => 선언(declare, define) + 할당(대입: assign)
		byte num1; // 선언
		num1 = 1; // 할당(대입)
		num1 = 2;
		
		// num1 = "a"; 
		// The value of the local variable num1 is not used.
		// 기본 자료형 <= 참조 자료형 (X)
		
		// num1 = 128; // (X)
		// Type mismatch: cannot convert from int to byte.
		// short 자료형(x), int 자료형(O) 정수형 기본 자료형 => int
		
		// num1 = (byte)128;  // 형변환(casting). 출력 = -128;
		// num1 = (byte)129;  // 형변환(casting). 출력 = -127;
		// num1 = (byte)-130; // 형변환(casting). 출력 = 126;
		
		num1 = 'a'; // 97 => ASCII코드.
		num1 = 'A'; // 65 => ASCII코드.
		num1 = '0'; // 48 => ASCII코드.
		
		num1 = 0x0a; 	// 10: 16진수(0x) => 10진수
		num1 = 011; 	// 9 : 8진수	(0)  => 10진수
		num1 = 0b111; 	// 7 : 2진수	(0b) => 10진수
				
		// num1 = "11"; // (X)
		// num1 = (byte)"11"; // (X)
		num1 = Byte.parseByte("11"); // (X)
		// byte : 기본 자료형
		// Byte : byte 기본 자료형의 랩퍼(wrapper) 클래스(참조 자료형)
		// 형변환(casting) 함수(메서드)
		// 자바의 함수는 메서드 형태로만 존재함
		
		System.out.println("num1 : " + num1);
		
	}

}

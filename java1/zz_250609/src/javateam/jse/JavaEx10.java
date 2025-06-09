package javateam.jse;

import java.util.Scanner;

public class JavaEx10 {

	public static void main(String[] args) {

		// < ?:(3항연산자) 연습>
		
		System.out.println("입력(달) : ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		String season ="";
		
		season = num >=3 && num <=5 ? "봄" :
				 num >=6 && num <=8 ? "여름" :
				 num >=9 && num <=11 ? "가을" :
				 num ==12 || num >=1 && num <=2 ? "겨울" :
				 "해당없음";
				
		System.out.printf("%d월 => %s%n", num , season);
		sc.close();
	}

}

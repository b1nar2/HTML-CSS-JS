package javateam.jse;

import java.util.Scanner;

public class JavaEx9 {

	public static void main(String[] args) {

		// <if문 연습>
		
		System.out.println("입력(달) : ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String season ="";

		if (num >=3 && num <=5) {
			season = "봄";
		} else if (num >=6 && num <= 8) {
			season = "여름";
		} else if (num >=9 && num <=11) {
			season = "가을";
		} else if (num ==12 || num >=1 && num <=2) {
			season = "겨울";
		} else {
			season = "해당 없음";
		}
		
		System.out.printf("%d월 => %s%n", num, season);

		sc.close();
	}

}

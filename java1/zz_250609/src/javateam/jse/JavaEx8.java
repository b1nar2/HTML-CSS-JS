package javateam.jse;

import java.util.Scanner;

public class JavaEx8 {

	public static void main(String[] args) {

		// <switch 문 연습>
		
		
		System.out.println("입력 : ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
//		System.out.println("num : "+num);

		/*
		String month ="";

		switch (num) {
		
		case 1 : month = num + "월"; break;
		case 2 : month = num + "월"; break;
		case 3 : month = num + "월"; break;
		
		default : month = "6월";
		}

		System.out.printf("%d => %s%n", num, month);
		
		sc.close();
	}
	*/
	
		String season ="";
	
		switch (num) {
		
		case 3 : case 4 : case 5 : season = "봄"; break;
		case 6 : case 7 : case 8 : season = "여름"; break;
		case 9 : case 10 : case 11 : season = "가을"; break;
		case 12 : case 1 : case 2 : season = "겨울"; break;
		
		default : season = "해당없음";
		}
	
		System.out.printf("%d월 => %s%n", num, season);
		
		sc.close();
	}
	
	
	

}

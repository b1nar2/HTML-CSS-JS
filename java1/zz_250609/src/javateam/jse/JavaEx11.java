package javateam.jse;

import java.util.Scanner;

public class JavaEx11 {

	public static void main(String[] args) {
		
		
		// <for문 - 합계>
		
		System.out.println("입력 : ");
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		int sum = 0;
		
		// for (int i = 1; i<= limit; i++) {
		// for (int i = 1; i<= limit; i+=2) { // 홀수
		// for (int i = 2; i<= limit; i+=2) { // 짝수
		for (int i = 2; i<= limit; i=i+2) { // 짝수	
			sum += i;
		} // for
		
		System.out.printf("1~%d까지의 합계 = %d%n" , limit, sum);
		sc.close();
		
/*
 		int sum = 0;
 		
		for (int i = 1; i<= 10; i++) {
		
		
		sum += i; // 같은표현: sum = sum + i;
		System.out.printf("1 ~ %d 까지의 합계 = %d%n", i, sum);
		//	System.out.printf(i + " ");
		}//
*/	
	
		
	}// main

}
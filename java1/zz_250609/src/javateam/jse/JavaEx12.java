package javateam.jse;

import java.util.Scanner;

public class JavaEx12 {

	public static void main(String[] args) {
		
		
		// <for문 - 합계>
		
		System.out.println("입력 : ");
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		int sum = 0;
		int i = 1;
		
		for (;;) {
			if (i <= limit) {
				// sum += i;
				// i++;
				sum += i++;
			} else {
				break;
			}
		}// for
		
		System.out.printf("1~%d까지의 합계 = %d%n" , limit, sum);
		sc.close();
		
	}// main

}
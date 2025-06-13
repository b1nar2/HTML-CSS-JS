package javateam.jse;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaEx35 {

	public static void main(String[] args) {
		
		Scanner sc = null;
		int num = -1;
		
		while (true) {
			
			try {
				System.out.print("점수 입력 : ");
				sc = new Scanner(System.in);
				String input = sc.next();
				
				// exit 입력하면 분기
				if (input.trim().toLowerCase().equals("exit")) {
					sc.close();
					break;
				} else {
					num = Integer.parseInt(input);	
				}				
				
				if (num >= 0 && num <= 100) {
					sc.close();
					break;
				} else {
					num = -1;
					throw new NumberFormatException("0 ~ 100사이의 정수를 입력하십시오.");
				}
				
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("0 ~ 100사이의 정수를 입력하십시오.");
				continue;
			}	
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				continue;
//			}
		} // while
		
		System.out.println("num : " + num);
	}

}
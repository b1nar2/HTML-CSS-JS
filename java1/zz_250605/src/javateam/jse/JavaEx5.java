package javateam.jse;

// import java.lang.*;
// import java.util.*;
import java.util.Scanner;

public class JavaEx5 {

	public static void main(String[] args) {
		
		System.out.print("글자 입력 : ");
		
		Scanner sc = new Scanner(System.in);
		// String input = sc.next(); // 문자열
		String input = sc.nextLine(); // 줄(문장) 단위
		// int input = sc.nextInt();
		
		System.out.println("input : " + input);
		
		
		sc.close(); // 키보드(표준) 입력 자원(resource) 반납
		
	}

}

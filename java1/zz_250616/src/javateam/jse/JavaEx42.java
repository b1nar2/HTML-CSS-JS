package javateam.jse;

import java.util.regex.Pattern;

public class JavaEx42 {

	public static void main(String[] args) {
		
		// 전화번호
		boolean result =
		Pattern.compile("010-\\d{4}-\\d{4}")
			   .matcher("010-1234-5678")
			   .matches();
		
		System.out.println("result : " + result);
	}

}

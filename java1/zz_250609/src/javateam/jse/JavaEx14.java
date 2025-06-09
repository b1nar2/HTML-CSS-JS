package javateam.jse;

public class JavaEx14 {

	public static void main(String[] args) {

		//<for문 -> while문 변환>
		
		int sum = 0;
		int i =1;
		
		while (i <= 10) {
			
			
			sum += i;
			i++;

		}// while
		
		System.out.println("1 ~ 10까지의 합 = " + sum);
	}

}

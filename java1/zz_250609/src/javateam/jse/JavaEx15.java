package javateam.jse;

public class JavaEx15 {

	public static void main(String[] args) {

		loop:
		for (int j = 1; j <=5; j++) {

			for (int i = 1; i <= 10; i++) {
				
//				if (i % 2 == 0) { // 짝수
				if (i % 2 == 1) { // 홀수
					
					// continue; // skip once
					break loop;
				}

				System.out.print(i + " ");
			} // for-i

			System.out.println("j=" + j);
		} //for-j

		System.out.println("끝");
	} // main

} // class

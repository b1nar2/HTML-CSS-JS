package javateam.jse;

public class JavaEx34 {

	public static void main(String[] args) {

		int num1 = 6;
		int num2 = (int)Math.random() * 10;
		int result = 0;
		int arr[] = new int[3];
		
		try {
		
			if(num2 == 0) {
				throw new Exception("0이 입력됨");
			}
			
			result = num1/num2;

			arr[4] = 1;

		} catch (ArithmeticException e) {
			System.out.println("msg : " + e.getMessage());
			System.out.println("e : " + e);
			e.printStackTrace(); // 예외가 발생한 위치와 원인을 출력해 주는 메서드
			System.err.println("메시지 : 0으로 나눔");
			
//		} catch (ArrayIndexOutOfBoundsException e) {
			
//			System.out.println("배열 인덱스 오류");
			
		} catch(Exception e) {
			System.out.println("기타 사건" + e);
			System.out.println("num1 : " + num1);
			num2 = 1;
			result=num1/num2;
		
		}
		
		System.out.println("end");
		
	} // main

}

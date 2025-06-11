package javateam.jse;

public class JavaEx25 {

	static void method(int ... nums) {	// 1)
//	static void method(int[] nums) {	// 2)
		for (int num : nums)
			System.out.print(num + " ");
	}
	
	
	public static void main(String[] args) {

		int []arr = {1,2,3};
		method(arr);
		method(1,2,3); // 1) => [O], 2) => [X]
		
	}

}

package javateam.jse;

import java.util.Arrays;

public class JavaEx20 {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 4, 5 };
		int arr2[] = new int[5];
		
//		arr2 = arr; // 얕은 복사(shallow copy)
//		arr2 = arr.clone(); // 깊은 복사(deep copy) - 요소 변경 => 원본과 사본 동시에 변경 (X)
//		System.arraycopy(arr, 0, arr2, 0, arr.length); // 깊은 복사
		arr2 = Arrays.copyOf(arr, arr.length); // 깊은 복사
		
		arr2[0] = 30;
		
		// 배열의 얕은 복사(shallow copy)
		// 요소 변경 => 원본과 사본 동시에 변경
		System.out.println("arr2[0] : " + arr2[0]);
		System.out.println("arr[0] : " + arr[0]);
	}

}

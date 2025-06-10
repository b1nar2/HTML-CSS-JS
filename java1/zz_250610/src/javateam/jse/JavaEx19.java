package javateam.jse;

public class JavaEx19 {

	public static void main(String[] args) {

		int arr2[][] = new int[3][];
		// int arr2[][] = new int[][2]; // (X)
		// 가변배열
//		arr2[0] = new int[3];
		arr2[0] = new int[] {1,2,3};
//		arr2[0] = {1,2,3}; // (X)
//		arr2[1] = new int[5];
		arr2[1] = new int[] {3,4,5,6,7};
//		arr2[2] = new int[2];
		arr2[2] = new int[] {8,9};
		
		for (int arr1[] : arr2) {
			for (int n : arr1) {
				System.out.print(n + " ");
			}
			System.out.println();
		}//
		
	}// main

}

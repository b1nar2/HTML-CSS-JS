package javateam.jse;

public class JavaEx17 {

	public static void main(String[] args) {

		// int arr[];
		// int[]arr;
		// int[5]arr;  // (X)
		int arr[] = new int[5];
		// arr = {1,2,3,4,5} //(X)
		arr = new int[]{1,2,3,4,5}; // (O)
		System.out.println(arr[0]);
		
		int arr2[] = {1,2,3,4,5};
		// int arr2[] = new int[]{1,2,3,4,5}; // (O)
		// int arr2[] = new int[5]{1,2,3,4,5}; // (X)
		
		System.out.println(arr2[0]);
		
	}

}

package javateam.jse;

public class JavaEx18 {

	public static void main(String[] args) {
		
//		int arr2[][]; // 2D(dimension: 차원)
//		arr2 = new int[3][2];// 행렬(matrix : row(행) * column(열))
		
//		int arr2[3][2] // (X)
//		int [][] arr2; // (O)
//		int []arr2[]; // (O)
		int arr2[][] = { {1,2},
						 {3,4},
						 {5,6} };
		
		System.out.println("배열의 첫 요소 : " + arr2[0][0]);
		System.out.println("배열의 크기(길이) : " + arr2.length); // 3 = 행(row)의 수가 출력됨.
		
		int arrSize = arr2.length * arr2[0].length;
		System.out.println("배열의 실제 요소 수 : " + arrSize);
		
//		// 전체 나열
//		for (int i = 0; i < arr2.length; i++) {
//			
//			for (int j = 0; j < arr2[i].length; j++) {
//				
//				System.out.print(arr2[i][j] + " ");
//			}// for j
//			
//			System.out.println();
//		}// for i
		
		
		// foreach loop(문) : 다음(next) 요소 검색 방식 (iterator)
		for (int []arr1 : arr2) { //
			
			for (int n : arr1) {
				
				System.out.print(n + " ");
			}
			
			System.out.println();
		} //
		
		
	}

}

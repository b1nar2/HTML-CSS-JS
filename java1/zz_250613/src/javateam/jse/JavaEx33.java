package javateam.jse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JavaEx33 {

	public static void main(String[] args) {

		String arr[] = {"오리", "죽전", "미금", "정자"};
		List<String> list = Arrays.asList(arr); // 리스트화
		
//		list.sort(Comparator.naturalOrder()); // 오름차순 정렬 {미금-오리-정자-죽전} 출력

//		ComparatorImpl com = new ComparatorImpl2();
		
		// new Comparator<String>(); => 생성자 아님
		// 인터페이스는 생성자를 보유하지 않음
		// 생성자 내부에서는 오버라이딩 금지!
		// 익명 클래스(anonymous Class)
		Comparator<String> com = new Comparator<String>() {
		
		@Override
		public int compare(String o1, String o2) {
//			return o1.compareTo(o2); // 오름차순 {미금-오리-정자-죽전} 출력
			return o2.compareTo(o1); // 내림차순 {죽전-정자-오리-미금} 출력
			
		}
		
	};
		
//		list.sort(com);
	
//		list.sort(new Comparator<String>() {
//			
//			@Override
//			public int compare(String o1, String o2) {
////				return o1.compareTo(o2); // 오름차순 {미금-오리-정자-죽전} 출력
//				return o2.compareTo(o1); // 내림차순 {죽전-정자-오리-미금} 출력)
//			}
//			
//		});
	
		// (람다대수)함수형 프로그래밍
		// Javascript : 화살표 함수와 유사
		list.sort((o1, o2) -> o1.compareTo(o2)); // 오름차순 {미금-오리-정자-죽전} 출력
		
		
		for (String s : list) System.out.println(s + " "); // 중괄호 생략
		
	}

}//class

// 	class ComparatorImpl2 implements Comparator<String> {

/*
	@Override
	public int compare(String o1, String o2) {
//		return o1.compareTo(o2); // 오름차순 {미금-오리-정자-죽전} 출력
		return o2.compareTo(o1); // 내림차순 {죽전-정자-오리-미금} 출력
		
	}
	
};
*/
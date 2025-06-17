package javateam.jse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JavaEx44 {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("죽전");
		list.add("오리");
		list.add("미금");
		list.addAll(Arrays.asList("정자", "수내", "이매", "서현", "야탑", "모란", "태평", "가천대"));

		list.add("미금"); // 중복 요소 삽입
		list.add("죽전"); // 중복 요소 삽입
		
		System.out.println("list의 크기 : " + list.size());
		System.out.println("포함여부 : " + list.contains("미금"));
		
		boolean result = list.containsAll(Arrays.asList(new String[] {"미금", "죽전", "오리"}));
		
		System.out.println("다수 요소 포함 여부 : " + result);
		
		// 해당 요소들을 리스트에서 제거
//		list.removeAll(Arrays.asList(new String[] {"미금", "죽전", "선릉"}));
		
		list.addFirst("한티"); // 첫 요소 추가
		list.addLast("중앙"); // 마지막 요소 추가
//		= list.add(0)
		
//		list.clear(); // 모든 요소들 삭제
		
//		System.out.println("list 공백 여부 : " + list.isEmpty());
		
		
//		list.subList(x, y); : x ~ y-1 까지 추출
//		index "y": 배제(exclusive)		
//		list.subList(0, 5).forEach(System.out::println); // :: 뒤에는 인자 X

//		list.replaceAll(x -> x.replace("한티", "왕십리")); // 한티 => 왕십리 변경
		
		// 공통 요소들만 추출 => 교집합
//		list.retainAll(Arrays.asList("왕십리", "미금", "개포동", "죽전"));
		
//		list.forEach(x -> System.out.print(x + " "));
		
		list.stream()
			.sorted() // 오름차순 정렬
			.limit(3) // 상위 3개만 추출
			.forEach(System.out::println); // 출력
				
	}

}

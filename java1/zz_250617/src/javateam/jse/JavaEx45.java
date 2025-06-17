package javateam.jse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JavaEx45 {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("죽전");
		list.add("오리");
		list.add("미금");
		list.addAll(Arrays.asList("정자", "수내", "이매", "서현", "야탑", "모란", "태평", "가천대"));

		list.add("미금"); // 중복 요소 삽입
		list.add("죽전"); // 중복 요소 삽입
		
//		Collections.sort(list); // 정렬(sorting, ordering)
		// 오름차순 정렬(ascending sort)
		// 기준 : 코드순(ex. utf-8 ...)

		/*
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
//				return o1.compareTo(o2); // 오름차순
				return o2.compareTo(o1); // 내림차순
			}
			
			
		});
		*/
		
		
//		Collections.sort(list, (o1, o2) -> o2.compareTo(o1)); // 내림차순
		
		
//		Collections.sort(list, Comparator.reverseOrder()); // 내림차순
//		Collections.sort(list, Comparator.naturalOrder()); // 오름차순
		
//		list.sort(Comparator.naturalOrder()); //오름차순
//		list.sort(Comparator.reverseOrder()); // 내림차순
		
//		Collections.shuffle(list); // 무작위로 요소들을 혼합(섞어줌)
		
//		list.forEach(x -> System.out.print(x + " "));
		
		// List => 배열 치환
//		String []arr = new String[list.size()];
//		arr = list.toArray(arr);
//		System.out.println("arr[0] : " + arr[0]);

		list.addAll(Arrays.asList(new String[] {"연신내", "대방", "신대방", "한양대"}));
		// "대" 라는 검색어가 포함된(contains) 역 이름을 추출, 오름차순으로 정렬한 후 결과를 리스트로 변환하여 출력함.
		list.stream()
			.filter(x -> x.contains("대"))	// "대"가 포함된 역만 필터링
			.sorted()						// 오름차순 정렬
			.toList()						// 결과를 리스트로 변환(Java 16이상)
			.forEach(System.out::println);
	}

}

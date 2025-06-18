package javateam.jse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaEx52 {

	public static void main(String[] args) {

		String foods[] = {"고등어", "오징어", "쌀", "파", "기장", "쇠고기", "찹쌀",
						  "고등어", "쌀", "파", "양파", "마늘", "삼치"};
		String grains[] = {"쌀", "보리", "귀리", "좁쌀", "수수", "기장", "찹쌀"};
		
		List<String> foodList=Arrays.asList(foods);
		List<String> grainList=Arrays.asList(grains);

		
		// 음식 중 곡물을 골라 오름차순으로 정렬하여 출력
		// foodList.stream()
		Stream.of(foods)
//			  .filter(x -> grainList.contains(x))
			  .collect(Collectors.groupingBy(x -> grainList.contains(x))) // 곡류인지 아닌지 분류
			  .get(true) // 곡류만 추출
			  .stream()
			  .distinct() // 중복 요소 제거
			  .map(x -> "곡류 : " + x) // "곡류" 라는 접두어 라벨을 붙여서 출력 => 곡류 : 기장 곡류 : 쌀 곡류 : 찹쌀
			  .sorted()
			  .forEach(x -> System.out.print(x + " "));
		
		System.out.println();
		
		// 음식 중 곡물이 아닌 것을 골라 오름차순으로 정렬하여 출력
		Stream.of(foods)
//		  .filter(x -> !grainList.contains(x)) => get(false)대신 !(not 연산자) 추가해 사용가능
		  .collect(Collectors.groupingBy((x -> grainList.contains(x)))) // true/false로 분류
		  // Collectors.groupingBy를 사용하면 스트림이 끝나고 Map 객체를 반환함 => 다시 .stream()으로 변환필요
		  .get(false) // 비곡류만 추출
		  .stream()
		  .distinct() // 중복 요소 제거
//		  .sorted() // 오름차순
		  .sorted(Comparator.reverseOrder()) // 내림차순
		  .map(x -> "비곡류 : " + x) // "비곡류" 라는 접두어 라벨을 붙여서 출력
		  .limit(3) // 상위 3개만 출력
		  .forEach(x -> System.out.println(x));
		
	}

}

	/* Stream 처리 구조 시각화
	 
		Stream.of(foods)
		    ↓
		.collect(Collectors.groupingBy(x -> grainList.contains(x)))
		    ↓
		Map<Boolean, List<String>> : true → 곡류, false → 비곡류
		    ↓
		.get(true) 또는 .get(false)
		    ↓
		.stream() → 스트림으로 변환
		    ↓
		.distinct() → 중복 제거
		    ↓
		.sorted() or sorted(Comparator.reverseOrder()) → 정렬
		    ↓
		.map(...) → 접두어 추가
		    ↓
		.limit(3) → 일부 제한
		    ↓
		.forEach(...) → 출력
		 
		 
	 */

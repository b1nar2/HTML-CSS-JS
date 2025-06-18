package javateam.jse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class JavaEx48 {

	public static void main(String[] args) {

		Set<String> set = new HashSet<>(); // HashSet은 요소의 순서를 보장하지 않음 => 순서 무작위
		set.add("java");
		set.add("java"); // set은 중복을 허용하지 않음 => 한 번만 저장됨
		set.add("java");
		set.add("python");
		set.add("python");
		set.add("html");
		set.add("css");
		
		System.out.println("set의 크기 : " + set.size()); // 4
	
		// set => 개별 요소 조회가 안 됨 : 배열, 리스트로 치환!
		// set => 배열 치환
		String []arr = new String[set.size()];
		arr = set.toArray(arr);
		System.out.println(arr[0]);

		
		// set => list
//		List<String> list = new ArrayList<>();
//		list.addAll(set);
//		System.out.println(list.get(0));
	
		// 전체 나열
		// foreach loop
//		for (String s : set) {
//			System.out.print(s + " ");
//		}
		
		
//		Iterator<String> it = set.iterator();
//		while (it.hasNext()) {
//			System.out.print(it.next() + " ");
//		}
//		

		// forEach 메서드
//		set.forEach(new Consumer<String>() {
//			@Override
//			public void accept(String t) {
//				System.out.print(t + " ");
//			}
//			
//		});
		
		
		// (람다(lambda) 대수) 함수형 프로그래밍
//		set.forEach(t ->System.out.print(t + " "));
//		set.forEach(System.out::println);
		
//		set.remove("java"); // 요소 제거
	
		System.out.println("요소 포함 여부 : " + set.contains("java"));
		
		
		boolean result
			= set.containsAll(Arrays.asList("java", "python"));
		System.out.println("요소들 포함 여부 : " + result);
	
		
//		set.clear(); // 모든 요소 삭제
//		System.out.println("set의 크기 : " + set.size());
		
		set.stream()
		   .filter(x -> !x.equals("java"))
		   .forEach(System.out::println);
		
		System.out.println();
		
		
	} //

}

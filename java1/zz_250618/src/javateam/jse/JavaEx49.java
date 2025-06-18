package javateam.jse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class JavaEx49 {

	public static void main(String[] args) {

		
		// 기본적으로 오름차순 정렬
		TreeSet<String> set = new TreeSet<>();
		String []arr = { "미금","정자","죽전","오리",
					     "수내","이매","야탑","모란",
					     "서현","태평","가천대" };
		set.addAll(Arrays.asList(arr));

//		set.addFirst("선릉"); // 컴파일에러. TreeSet은 addFirst 지원하지 않음.
//		set.addLast("도곡"); // 컴파일에러.
		
		// 역순(내림차순) 정렬(reverse order(sort))
//		Iterator<String> it = set.descendingIterator();
//		while(it.hasNext()) {
//			System.out.print(it.next() + " ");
//		}

		
		
//		set.descendingSet()
//		   .forEach(x -> System.out.print(x + " "));
		
//		set.stream()
//		   .sorted(Comparator.reverseOrder())
//		   .forEach(x -> System.out.print(x + " "));
	
		// 요소들의 일부분 추출
//		System.out.println(set.ceiling("이매리")); // 정자
//		System.out.println(set.floor("수정")); // 수내

//		set.subSet("수정", "죽전")
//		set.subSet("수정", true, "죽전", true)
//		   .forEach(x -> System.out.print(x + " "));
		
//		set.headSet("수내")
//		set.headSet("수내", true) // true : 수내까지 포함
//		   .forEach(x -> System.out.print(x + " "));
		
		set.tailSet("수내")
//		set.tailSet("수내" ,false) // false : 수내 미포함
		   .forEach(x -> System.out.print(x + " "));
		
		System.out.println();
		
		set.forEach(x -> System.out.print(x + " "));

	} //

}

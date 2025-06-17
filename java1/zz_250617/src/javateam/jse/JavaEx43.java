package javateam.jse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class JavaEx43 {

	public static void main(String[] args) {

//		List list; // raw type => 제네릭을 사용하지 않은 경우
		String[]arr = {"java", "python", "html", "typescript"};
//		List<String> list = Arrays.asList(arr); // *1)
		
//		List<String> list = new ArrayList<String>(); // (O)
		List<String> list = new ArrayList<>(); // (O)  *2)
//		List<> list = new ArrayList<String>(); // (X)
//		List<> list = new ArrayList<>(); // (X)
		// 배열 => List 변환(불변 list: 캡슐화)
		// List<E> => List<String> : 요소(Element)가 String
		
		// 배열의 요소들을 한꺼번에 추가
		list.addAll(Arrays.asList(arr));
		// addAll 인자
		// Collection<? extends String> <= List<String>
		// 자동 업캐스팅(형변환), 공변성(: 다형성)
		
		list.add("javascript"); // *1) 추가(X) 불변 리스트. *2) (O)
		// 1) 에러(예외) 유발: UnsupportedOperationException 예외 발생
		
		list.remove(0); // *1) 제거(X) 불변 리스트. *2) (O)
		list.set(0,"CSS"); // *1,2) 변경(O) 변경 가능
//		System.out.println(list.get(0)); // 첫 번째 요소 조회(검색)
		
		
		// 전체 나열
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.printf(list.get(i) + " ");
//		}
		
		//foreach loop : 내부적으로 반복자 패턴(iterator)wjrdyd
//		for (String s : list) {
//			System.out.print(s + " ");
//		}
		
		//foreach 메서드 : 반복자 패턴 적용
		// 람다대수(기호식) 함수형 프로그래밍
		// ECMAscript(ES) 화살표 함수와 유사
//		list.forEach(e -> System.out.print(e + " "));
		
//		Consumer<String> con = new ConsumerImpl();
//		list.forEach(con);

		// 함수형 인터페이스 : 구현할 추상 메서드가 한 개인 인터페이스
		// Consumer: 값을 받아 소비(처리)한 후 값을 반환하지 않음(리턴값 없음: void)
//		list.forEach(new Consumer<String>()  {
//			@Override
//			public void accept(String t) {
//				System.out.print(t + " ");
//			}
//			
//		});

//		list.forEach(t -> System.out.print(t + " "));
		
		// Iterator (반복자 패턴: 행위 패턴)
		Iterator<String> it = list.iterator();
		
//		while (it.hasNext()) {
//			System.out.print(it.next() + " ");
//		}
		
		// 역순(revers) 나열
		ListIterator<String> lit = list.listIterator(list.size()); // list.size => 0 ~ n-1
		
		while (lit.hasPrevious()) {
			System.out.print(lit.previous() + " ");
		}
		
		System.out.println();
		
	}//

}// calss

class ConsumerImpl implements Consumer<String> {
	
	@Override
	public void accept(String t) {
		System.out.println(t + " ");
	}
}
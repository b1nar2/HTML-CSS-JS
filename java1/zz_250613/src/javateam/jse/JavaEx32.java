package javateam.jse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JavaEx32 {

	public static void main(String[] args) {

		String arr[] = {"오리", "죽전", "미금", "정자"};
		List<String> list = Arrays.asList(arr); // 리스트화
		
//		list.sort(Comparator.naturalOrder()); // 오름차순 정렬 {미금-오리-정자-죽전} 출력

//		ComparatorImpl com = new ComparatorImpl();
		Comparator<String> com = new ComparatorImpl();
		list.sort(com);
		
		for (String s : list) System.out.println(s + " "); // 중괄호 생략
		
	}

}//class

class ComparatorImpl implements Comparator<String> {
	
	@Override
	public int compare(String o1, String o2) {
//		return o1.compareTo(o2); // 오름차순 {미금-오리-정자-죽전} 출력
		return o2.compareTo(o1); // 내림차순 {죽전-정자-오리-미금} 출력
		
	}
	
}

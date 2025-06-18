package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Demo11 {

	public static void main(String[] args) {

		
		Map<String, Integer> student = new HashMap<>();
		student.put("홍길동", 85);
		student.put("김철수", 90);
		student.put("이영희", 78);
		student.put("박민수", 92);
		student.put("최지우", 88);
	
		System.out.println("학생 이름 및 점수 : " + student);

		Set<Entry<String, Integer>> set = student.entrySet();
		
	}

	
}

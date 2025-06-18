package javateam.jse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JavaEx50 {

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<>();
		map.put("응암", "감자탕");
		map.put("종로", "설렁탕");
		map.put("마포", "돼지갈비");
		map.put("노량진", "회");
		map.put("회현", "고갈비");
		map.put("마장", "육고기");
		map.put("신당", "떡볶이");
		map.put("신림", "순대");
		map.put("무교", "낙지볶음");
		map.put("장충", "족발");

		map.put("종로", "닭한마리");
		map.put("노량진", "컵밥");

		map.put("모란", "돼지부속");

				
		System.out.println("map의 크기 : " + map.size());
		
//		System.out.println(map.get("종로"));

		// 전체 나열
		Set<Entry<String, String>> set = map.entrySet();
//		Set<Map.Entry<String, String>> set = map.entrySet();
		
		
		Iterator<Entry<String, String>> it = set.iterator();
		
		while(it.hasNext()) {
			
//			Entry<String, String> entry = it.next();
//			String key = entry.getKey();
//			String value = entry.getValue();

			
//			System.out.println(key + " = " + value);

			
//			Entry<?, ?> entry = it.next();
//			String key = (String) entry.getKey();
//			String value = (String) entry.getValue();
			
			Entry<? extends String, ? extends String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();			
			
			System.out.println(key + " = " + value);
		}

		System.out.println("------------------------");
		
//		map.entrySet().forEach(x -> System.out.println(x)); // entrySet: key=value 형식으로 자동 출력
//		map.entrySet().forEach(System.out::println); // entrySet: key=value 형식으로 자동 출력

		
//		map.forEach((k, v) -> System.out.println(k + "=" + v));
//		map.keySet().forEach(k->System.out.println(k + "=" + map.get(k)));

		// 값(value)들만 출력
//		map.values().forEach(System.out::println);

		map.putIfAbsent("종로", "도가니탕"); // putIfAbsent : 해당 key가 존재하지 않을 때에만 값을 추가.
		System.out.println(map.get("종로")); // 닭한마리 (이미 존재하고 있으므로 추가되지 않음)
		
	} //

}

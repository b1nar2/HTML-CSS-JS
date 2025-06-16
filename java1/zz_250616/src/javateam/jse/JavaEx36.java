package javateam.jse;

import java.util.Date;
import java.util.Map;
import java.util.Properties;




public class JavaEx36 {

	public static void main(String[] args) {

//		System.out.println(System.currentTimeMillis());
		System.out.println(new Date(System.currentTimeMillis()));
		
		
		// 시스템 속성
		Properties props = System.getProperties();
		
//		props.forEach((k, v)
//			  -> System.out.println(k + "=" + v));
		
		System.out.println(props.getProperty("stdout.encoding"));
		
		// 시스테 환경설정 정보
		Map<String, String> map = System.getenv();
		map.forEach((k, v)
			-> System.out.println(k + "=" + v));
	}

}

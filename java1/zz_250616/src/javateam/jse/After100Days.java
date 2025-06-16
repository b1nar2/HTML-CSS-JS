package javateam.jse;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
public class After100Days {
 
    public static void main(String[] args) {
 
    	// 기존 날짜 클래스들을 활용하는 법
        Calendar cal = Calendar.getInstance();
        
		// 단계-1) 
		// 위의 cal 인스턴스를 활용하여 년월일 성분을 구하고 
		// 각각 year, month, date 라는 정수형 변수에 대입합니다.
		// Hint) Calendar 클래스의 get 멤버 메서드와 
        // Calendar의 멤버 필드(YEAR, MONTH, DATE) 성분을 활용합니다.
        // 참고) https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
        // 주의) 월(달) 성분은 0부터 시작하기 때문에 1을 더해야 합니다.
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // month=> 0~11. +1 필요.
        int date = cal.get(Calendar.DATE);
        
        // printf 메서드를 활용하여 아래와 같은 형식으로 화면에 출력합니다.
        // ex) 2023년 07월 25일
        System.out.printf("%d년 %d월 %d일\n", year, month, date);
        System.out.printf(String.format("%1$tY년 %1$tm월 %1$te일\n", cal));
        System.out.printf("%1$tY년 %1$tm월 %1$te일\n", cal);
        System.out.printf("%1$tY년 %1$tb %1$te일\n", cal);
        
        System.out.println("-----------------------------");
        
        // 단계-2)
     	// 금일 날짜로부터 100일후에 해당되는 연산을 수행합니다.
     	// Hint) Calendar 클래스의 set, add 멤버 메서드를 활용합니다.
        // 주의사항) 월(month) 성분 입력시 -1을 빼고 입력합니다.
        // add 메서드 이용시 첫번째 인자로 Calendar.DATE혹은 Calendar.DAY_OF_MONTH 필드를 사용합니다.
        cal.set(year, month-1, date); // (1)
        cal.add(Calendar.DATE, 100); // 100일 후
        
		// 단계-3)
		// 위에서 구한 날짜를 Date Formatter 클래스를 활용하여 
		// 아래와 같은 형식으로 화면에 출력합니다.
		// ex) 2023년 O월 O일
		// Hint) SimpleDateFormat 클래스를 활용합니다.
        // 참고) https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat dt = new SimpleDateFormat("yyyy년 M월 d일");
        String str = dt.format(cal.getTime());
        System.out.println(str);        
        
        //////////////////////////////////////////////////////////////////
        
     	// time 패키지의 날짜 클래스들을 활용하는 법
        // 단계-4)
        // DateTimeFormatter를 활용하여 아래와 같이 출력될 수 있도록 날짜 포맷을 
		// 설정합니다.
		// ex) 2023년 O월 OO일
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
        
        // 단계-5 : LocalDateTime의 멤버 메서드(now)를 활용하여 금일 날짜를 구하여 
 		// format 멤버 메서드와 위에서 작성한 날짜 포맷 객체를 메서드 인자로 활용하여 금일 날짜를 인쇄합니다.
 		LocalDateTime localDateTime = LocalDateTime.now();		
 		System.out.println(localDateTime.format(dateTimeFormatter));
 		
 		// 단계-6 : 금일로부터 100일이후의 날짜를 LocalDateTime 멤버 메서드(plusDays)를 활용하여
		// 구한후 위와 동일하게 포맷터의 메서드(format)를 활용하여 포맷 객체를 인자로 입력하여 구한 날짜를 인쇄합니다.
		System.out.println(localDateTime.plusDays(100).format(dateTimeFormatter));
        
    } // main
 
}
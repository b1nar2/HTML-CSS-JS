package zz_250619;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaEx55 {

	public static void main(String[] args) /* 3) */ throws FileNotFoundException {
	
		int ch;
		
		// try ~ with Resources문
		// 자원 자동 반납(close)
		// AutoCloseable 인터페이스와의 상속/구현 관계를 전제
		
	// 1)	
//		try (FileInputStream fis = new FileInputStream ("./demo.txt");
//			 InputStreamReader isr = new InputStreamReader(fis);)
		
	// 2)**
//		try (InputStreamReader isr
//				  = new InputStreamReader(new FileInputStream ("./demo.txt"));)
		
	// 3)	
		InputStreamReader isr
			= new InputStreamReader(new FileInputStream ("./demo.txt"));
		try (isr) { // since Java9
			
			// 스트림의 끝 (eos)이 아니라면(: 읽을 데이터가 있다면)
			 while ((ch = isr.read()) != -1) { // isr
			
				System.out.print((char)ch); // 콘솔 출력
				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("파일 처리 에러 : " + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			System.err.println("입출력 처리 에러 : " + e.getMessage());
			e.printStackTrace();
			
		}
	}
}

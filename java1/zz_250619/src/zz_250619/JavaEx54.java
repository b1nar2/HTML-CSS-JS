package zz_250619;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaEx54 {

	public static void main(String[] args) {

		FileInputStream fis = null;
		// stream-> 바이트 기반. 한글 처리할 수 없음.
		// 한글 처리 가능 stream : InputStreamReader
		InputStreamReader isr = null;
		
		int ch;
		
		try {
			
			fis = new FileInputStream("./demo.txt");
			isr = new InputStreamReader(fis); // 보조스트림
		
			// 파일 끝 (eof)이 아니라면(: 읽을 데이터가 있다면)
			// while ((ch = fis.read()) != -1) { // fis
			
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
			
		} finally {
			try {
				fis.close();
				
			} catch (IOException e) {
				System.err.println("자원반납 에러 : " + e);
				e.printStackTrace();
			} //
		}
		
	}

}

package zz_250619;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaEx56 {

	public static void main(String[] args) throws IOException{

//		try {

			String str = Files.readString(Paths.get("demo.txt"));
			System.out.println(str);
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}

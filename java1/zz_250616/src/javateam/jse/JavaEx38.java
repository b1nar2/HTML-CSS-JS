package javateam.jse;

import java.util.StringTokenizer;

public class JavaEx38 {

	public static void main(String[] args) {

		String str = " 6월 16일은, 월요일이다.";
		str= str.concat("6월 17일은, 화요일이다.");
		
		StringTokenizer st = new StringTokenizer(str, ",.");
		
		while (st.hasMoreTokens()) {
//		while (st.hasMaorTokens()==true) {
			System.out.println(st.nextToken());
		}
		
	}

}

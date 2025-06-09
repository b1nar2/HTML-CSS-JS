package javateam.jse;

public class JavaEx16 {

	public static void main(String[] args) {

		String str = "java";
		String str1 = "java";
		String str2 = new String("java");
		
		
		System.out.println(str == str1); // true
		System.out.println(str == str2); // false
		System.out.println(str.equals(str2)); // true
		System.out.println(str.contentEquals(str2)); // true
		
		
		System.out.println(str.charAt(1)); // a
		System.out.println("java".charAt(1)); // a
	}

}

package javateam.jse;

public class JavaEx37 {

	public static void main(String[] args) {
		
		String str = "java";
		str += " python";
		System.out.println("str : " + str);
		
		StringBuilder st = new StringBuilder();
//		st.append("java");
//		st.append(" python");
		st.append("java")
		  .append(" python");
		
		System.out.println("str : " + st);
	}

}

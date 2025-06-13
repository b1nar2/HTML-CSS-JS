package javateam.jse;

public class JavaEx31 {

	void localMethod() {
		
		class LocalClass {
			
			String name;
			LocalClass() {
				System.out.println("LocalClass 생성자");
			}
			void method() {
			}
			
		} // class LocalClass
		
		LocalClass local = new LocalClass();
		local.method();

	} // void localMethod
	
	public static void main(String[] args) {
		
	}

}

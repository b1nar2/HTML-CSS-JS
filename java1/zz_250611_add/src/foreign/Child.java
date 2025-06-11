package foreign;

import home.Parent;

public class Child extends Parent {
	
	// String name = new Parent().name;
	String name = super.name; // protected는 상속을 전제로 해야 => 접근 가능
	  
	
}

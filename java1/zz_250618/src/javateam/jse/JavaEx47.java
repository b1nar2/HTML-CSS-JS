package javateam.jse;

import java.util.LinkedList;
import java.util.Queue;

public class JavaEx47 {

	public static void main(String[] args) {

		Queue<String> qu = new LinkedList<>();
		qu.offer("java");
		qu.offer("python");
		qu.offer("javascript");
		
		// FIFO(First In First Out)
		System.out.println(qu.poll()); // java
		System.out.println(qu.size()); // 2
		
		System.out.println(qu.poll()); // python
		System.out.println(qu.size()); // 1
		
		System.out.println(qu.poll()); // javascript
		System.out.println(qu.size()); // 0
	}

}

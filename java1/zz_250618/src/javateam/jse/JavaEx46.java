package javateam.jse;

import java.util.Stack;

public class JavaEx46 {

	public static void main(String[] args) {

		Stack<String> stack = new Stack<>();
		stack.push("java");
		stack.push("python");
		stack.push("javascript");
		
		// LIFO(Last in First Out)
		System.out.println(stack.pop());
		System.out.println(stack.size()); // 2

		System.out.println(stack.pop());
		System.out.println(stack.size()); // 1
		
		System.out.println(stack.pop());
		System.out.println(stack.size()); // 0
		
		
		
		
	}

}

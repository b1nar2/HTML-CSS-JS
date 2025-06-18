package javateam.jse;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class JavaEx51 {

	public static void main(String[] args) {

		SequencedSet<String> set = new LinkedHashSet<>();
		set.add("도곡");
		set.addFirst("모란");
		set.add("한티");
		set.addLast("가천대");
		
		set.forEach(x -> System.out.print(x + " "));
		
	}

}

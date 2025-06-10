package javateam.jse;

public class JavaEx21 {

	// String [] args : 외부 인자
	// java(.exe) JavaEx21(.class) 인자1 인자2 인자3
	public static void main(String[] args) {

		// 외부 인자 입력
		// Run Configurations => Java Application => Arguments(인자) => Variables => String_prompt (= ${string_prompt})
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}

}

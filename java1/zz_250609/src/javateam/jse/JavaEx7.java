package javateam.jse;

public class JavaEx7 {

	public static void main(String[] args) {

		int num = 3;
		// if (true) {
		// if (false) { // 실행(X) => Dead Code

		// if (2 > 3) { // 실행(X) => Dead Code
		// if (num == 3) {
		// if (num = 4) // (X)
		// if (!num == 3) { // (X)
		// if (!(num == 3)) { // (O) => num !=3
		if (num>=3) {
			System.out.println("무조건 실행");
		} else if (num>=2) {
			System.out.println("2 이상");
		} else { // 기타 등등(etc)
			System.out.println("etc");
		}
	} //main

}

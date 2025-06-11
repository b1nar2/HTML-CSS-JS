package javateam.jse;

public class CalcUse {
	
	// CalcClass calcClass = new CalcClass();  // 1)	=> 객체를 바로 생성, 재사용 됨
	CalcClass calcClass; //2)	=> 필드로 선언만 함(참조변수 선언)
	
	public int calc(String operator, int x, int y) {
		
		int result = 0;
		calcClass = new CalcClass(); // 2) 메서드 내부에서 객체 생성(연산을 할 때마다 생성)
		
		switch (operator) {
		
		default : // result = calcClass.add(x, y); break;
		case "+" : result = calcClass.add(x, y); break;
		case "-" : result = calcClass.subtract(x, y); break;
		case "*" : result = calcClass.multiply(x, y); break;
		case "/" : result = calcClass.divide(x, y); break;
		case "%" : result = calcClass.remainder(x, y); break;

		}
		return result;
	}

	public static void main(String[] args) {

		// System.out.println(new CalcUse().calc("&",1,2));
		CalcUse calcUse = new CalcUse();
		System.out.println("덧셈의 결과 : "+ calcUse.calc("+", 1, 2));
		System.out.println("뺄셈의 결과 : " + calcUse.calc("-", 2, 1));
		System.out.println("곱셈의 결과 : " + calcUse.calc("*", 2, 3));
		System.out.println("나눗셈의 결과 : " + calcUse.calc("/", 4, 2));
		System.out.println("나머지 연산의 결과 : " + calcUse.calc("%", 3, 2));
	}

}

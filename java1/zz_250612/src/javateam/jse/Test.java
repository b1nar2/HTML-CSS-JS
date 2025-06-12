package javateam.jse;

public class Test {

	public static void main(String[] args) {

		// Director work = new Director(); // (X)
		// 공변성, 업캐스팅
		// Director work = new Worker(); // (O) 1)
		Worker work = new Worker(); // (O) 2)
//		work.order();
//		work.workerMethod(); // 1) (X) 2) (O)
		work.defaultMethod();
		
		Director.staticMethod();
		
	}

}
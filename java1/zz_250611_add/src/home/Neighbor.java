package home;

public class Neighbor {


		String name = new Parent().name; // 접근 가시성 : access visibility => 보안 : private은 접근(X)

		void neighborMethod() {
			System.out.println("neighborMethod");
		}

} // class
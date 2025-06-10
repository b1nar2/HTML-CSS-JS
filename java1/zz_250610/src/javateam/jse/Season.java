package javateam.jse;

// enumeration => enum 열거형 : 상수들의 묶음
public enum Season {

// class에서의 상수 = > public static final String Spring = " Spring";
	
	Spring("봄"),
	Summer("여름"),
	Fall("가을"),
	Winter("겨울");
	
	// private(클래스 내부에서만 적용) => 보안
	// 접근 제어자(access modifier)
	private String season;
	
	private Season(String season) {
		this.season = season;
	}
	public String getSeason() {
		return season;
	}
}

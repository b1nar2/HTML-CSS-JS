package com.javateam.springBootInterceptorValidator.domain;

import org.hibernate.validator.constraints.Range;
//import org.springframework.format.annotation.DateTimeFormat;

//import jakarta.validation.constraints.DecimalMax;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.Digits;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Book {

	@NotNull
	// ex) ISBN1186710454 (ISBN-10) 혹은 ISBN9791186710456 (ISBN-13)
	@Pattern(regexp="ISBN(97(8|9))?\\d{9}(\\d|X)", 
			 // message="ISBN-10 혹은 ISBN-13 형식에 맞추어서 입력하십시오.")
			message="{book.bookId.message}")
	private String bookId;     // 도서ID
	
	@NotNull
	// 주의) 띄워쓰기(" ")포함
	@Pattern(regexp="[a-zA-Z0-9 가-힣]{2,20}", 
			 message="{book.name.message}")
    private String name;      // 도서명
	
	@NotNull
//	@DecimalMin(value="1000", message="최저 가격은 1,000원입니다.")
//	@DecimalMax(value="1000000", message="최대 가격은 1,000,000원입니다.")
	@Range(min=1000, max=1000000, message="{book.unitPrice.message}")
    // private int unitPrice;      // 가격
	private int unitPrice;      // 가격
	
	@NotNull
	// 주의) 띄워쓰기(" ")포함
	@Pattern(regexp="[가-힣 ]{2,20}", 
			 message="{book.author.message}")
    private String author;     // 저자
	
	@NotNull
	// 주의) 특수문자 허용
	@Pattern(regexp="[a-zA-Z0-9가-힣\\W\\s]{2,200}", 
			 message="{book.description.message}")
    private String description; // 설명
	
	@NotNull
	@Pattern(regexp="[a-zA-Z0-9 가-힣]{2,20}", 
			 message="{book.publisher.message}")
    private String publisher;   // 출판사
    
	@NotNull
	@Pattern(regexp="[a-zA-Z0-9 가-힣]{2,20}", 
			 message="{book.category.message}")
    private String category;    // 분류
    
	@NotNull
	@Range(min=1, max=1000, message="{book.unitsInStock.message}")
    private long unitsInStock;  // 재고수
    
	// @Past(message="출판일은 금일 기준 이전 일이 들어가야 합니다")
	@NotNull
	@Pattern(regexp="([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))", 
	 		 message="{book.releaseDate.message}")
    private String releaseDate; // 출판일(월/년)
    
	// 라디오 버튼의 경우는 기본값(기정값: checked="checked" 속성이 있는 항목)이 선정되어 있으면 폼점검 불필요
    private String condition;   // 신규 도서 or 중고 도서 or 전자책
    
}
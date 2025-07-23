/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 조인 전략(Join Strategy)
 * 
 * 1. 장점
 * - 테이블이 정규화 된다.
 * - 외래 키 참조 무결성 제약조건을 활용할 수 있다.
 * - 저장공간을 효율적으로 사용한다.
 * 
 * 2. 단점 
 * - 조회할 경우 조인이 많이 사용되므로 성능이 저하될 수 있다.
 * - 조회 쿼리가 복잡하다.
 * - 데이터를 등록할 INSERT SQL를 두 번 실행한다.
 * 
 * 3. 특징
 * - JPA 표준 명세는 구분 컬럼을 사용하도록 하지만 하이버네이트를 포함한 
 * 몇몇 구현체는 구분 컬럼(@DiscriminatorColumn)없이도 동작한다.
 * 
 * 4. 관련 annotation
 * - @PrimaryKeyJoinColumn, @DiscriminatorColumn, @DiscriminatorValue
 * 
 * @author javateam
 *
 */
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B") // Entity를 저장할 때 구분 컬럼에 입력할 값을 지정.
//cf) @DiscriminatorColumn(name="DTYPE") 
//부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분 할 수 있다.
//기본값은 DTYPE이므로 @DiscriminatorColumn을 줄여서 사용할 수 있다.
public class Book extends Item {

    private String author;
    private String isbn;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Book [author=%s, isbn=%s]", author, isbn);
	}

}

/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * @author javateam
 *
 */
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M") // Entity를 저장할 때 구분 컬럼에 입력할 값을 지정.
//cf) @DiscriminatorColumn(name="DTYPE") 
//부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분 할 수 있다.
//기본값은 DTYPE이므로 @DiscriminatorColumn을 줄여서 사용할 수 있다.
public class Movie extends Item {

    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Movie [director=%s, actor=%s]", director, actor);
	}
    
}
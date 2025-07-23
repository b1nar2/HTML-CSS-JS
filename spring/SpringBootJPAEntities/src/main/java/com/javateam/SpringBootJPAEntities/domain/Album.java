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
@DiscriminatorValue("A") // Entity를 저장할 때 구분 컬럼에 입력할 값을 지정.
// cf) @DiscriminatorColumn(name="DTYPE") 
// 부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분 할 수 있다.
// 기본값은 DTYPE이므로 @DiscriminatorColumn을 줄여서 사용할 수 있다.
public class Album extends Item {

    private String artist;
    private String etc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Album [artist=%s, etc=%s]", artist, etc);
	}

}
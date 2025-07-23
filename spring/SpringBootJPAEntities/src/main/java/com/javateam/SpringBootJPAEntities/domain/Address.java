/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 주소 : 값 타입(임베디드 타입). 회원과 배송(Delivery)에서 사용한다.
 * 
 * @author javateam
 *
 */
import jakarta.persistence.Embeddable;

@Embeddable // 값 타입을 정의하는 곳.  
// cf) @Embedded 값 타입을 사용하는 곳 -> hibernate에서는 임베디드 타입을 components라고 함.  
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Address [city=%s, street=%s, zipcode=%s]", city, street, zipcode);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (zipcode == null) {
			if (other.zipcode != null) {
				return false;
			}
		} else if (!zipcode.equals(other.zipcode)) {
			return false;
		}
		return true;
	}

}
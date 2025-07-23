/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 회원 : 이름과 주문한 상품들 그리고 임베디드 타입인 Address를 가진다.
 * 
 * @author javateam
 *
 */
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id 
    @GeneratedValue
    @Column(name = "MEMBER_ID", precision=4, scale=0)
    private BigDecimal id;

    private String name;

    @Embedded 
    //@Embedded 값 타입을 사용하는 곳 -> hibernate에서는 임베디드 타입을 components라고 함.
    // cf) @Embeddable // 값 타입을 정의하는 곳.  
    private Address address;

    @OneToMany(mappedBy = "member") // "1:다" 관계(relation)
    private List<Order> orders = new ArrayList<Order>();


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Member [id=%s, name=%s, address=%s, orders=%s]", id, name, address, orders);
	}

}

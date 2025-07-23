/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 배송 : 주문 시 하나의 배송 정보를 생성한다. 주문과 배송은 1:1 관계이다.
 * 
 * @author javateam
 */
import java.math.BigDecimal;

/**
 * @author javateam
 *
 */
import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID", precision=4, scale=0)
    private BigDecimal id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

    public Delivery() {
    }

    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Delivery [id=%s, order=%s, address=%s]", id, order, address);
	}

}

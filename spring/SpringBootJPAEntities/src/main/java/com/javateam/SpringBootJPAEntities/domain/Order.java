/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 주문 : 한 번 주문시 여러 상품을 주문할 수 있으므로 
 *      주문과 주문 상품(OrderItem)은 "1:다" 관계이다.
 * 주문은 상품을 주문한 회원과 배송 정보, 주문 날짜, 주문 상태(status)를 가지고 있다. 
 * 주문 상태는 열거형을 사용했는데 주문(Order), 취소(Cancel)을 표현할 수 있다.
 * 
 * @author javateam
 *
 */
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID", precision=4, scale=0)
    private BigDecimal id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;      //주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;  //배송정보

    private Date orderDate;     //주문시간

    @Enumerated(EnumType.STRING) 
    // @Enumerated을 사용하면 편리하게 enum 타입(열거형)의 데이터를 저장할 수 있다. 
    private OrderStatus status;//주문상태

	 /**
	  * 주문 생성 메서드
	  * @param member 회원정보(VO)
	  * @param delivery 배송
	  * @param orderItems 주문 상품(들)
	  * @return 주문 정보
	  */
    public static Order createOrder(Member member, 
    								Delivery delivery, 
    								OrderItem... orderItems) {

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());
        return order;
    }

    // Business Logic
    /**
     * 주문 취소
     */
    public void cancel() {

        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    /**
     * 전체 주문 가격 조회
     * @return 전제 주문 가격
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    // 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Order [id=%s, member=%s, orderItems=%s, delivery=%s, orderDate=%s, status=%s]", id,
				member, orderItems, delivery, orderDate, status);
	}
 
}
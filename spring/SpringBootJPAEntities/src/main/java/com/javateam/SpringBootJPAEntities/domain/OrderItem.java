/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 주문 상품 : 주문한 상품 정보와 주문 금액(orderPrice), 주문 수량(count) 정보를 가지고 있다.
 * 
 * 즉시 로딩(eager loading)과 지연 로딩(lazy loading)
 * 
 * 1. 즉시 로딩(eager loading)
 * 
 * - entity를 조회할 때 연관된 entity도 함께 조회한다.
 * ex) em.find(Member.class, "member1")를 호출할 때 회원 entity와 연관된
 *     팀 entity도 함께 조회한다.
 * - 설정 방법 : @ManyToOne(fetch=FetchType.EAGER)
 * 
 * 2. 지연 로딩(Lazy loading)
 * 
 * - 연관된 entity를 실제 사용할 때 조회한다.
 * ex) member.getTeam().getName()처럼 조회한 팀 entity를 실제 사용하는
 *     시점에 JPA가 SQL을 호출해서 팀(team) entity를 조회한다.
 * - 설정 방법 : @ManyToOne(fetch=FetchType.LAZY)     
 * 
 * @author javateam
 *
 */
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id 
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID", precision=4, scale=0)
    private BigDecimal id;

    @ManyToOne(fetch = FetchType.LAZY)
    // 지연 로딩
    @JoinColumn(name = "ITEM_ID")
    private Item item;      //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;    //주문

    private int orderPrice; //주문 가격
    private int count;      //주문 수량

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    
    /**
     * 주문 취소
     */
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 주문 상품 전체 가격 조회
     * @return 주문 상품 전체 가격
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int buyPrice) {
        this.orderPrice = buyPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("OrderItem [id=%s, order=%s, orderPrice=%s, count=%s]", id, order, orderPrice, count);
	}

}
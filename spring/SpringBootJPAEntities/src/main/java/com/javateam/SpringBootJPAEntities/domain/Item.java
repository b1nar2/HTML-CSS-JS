/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 상품 : 이름, 가격, 재고수량(stockQuantity)을 가지고 있다. 
 * 상품을 주문하면 재고수량이 줄어든다. 
 * 
 * 상품의 종류로는 도서(Book), 음반(Album), 영화(Movie)가 있는데,
 * 각각은 속성이 조금씩 다르다.
 * 
 ** 단일 테이블 전략(Single Table Strategy)
 * 
 * 1. 장점
 * - 조인이 필요 없으므로 일반적으로 조회 성능이 빠르다.
 * - 조회 쿼리가 단순하다.
 * 
 * 2. 단점
 * - 자식  Entity가 매핑(mapping)한 컬럼은 모두 null을 허용해야 한다.
 * - 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있다.
 *   그러므로 상황에 따라서는 조회 성능이 오히려 느려질 수 있다.
 *   
 * 3. 특징
 * - 구분 컬럼(@DiscriminatorColumn)을 꼭 사용해야 한다.
 * 따라서 @DiscriminatorColumn을 꼭 설정해야 한다.
 * - @DiscriminatorColumn을 지정하지 않으면 기본으로 Entity이름을 사용한다.
 *    
 *   
 * 
 * @author javateam
 *
 */
import jakarta.persistence.*;

import com.javateam.SpringBootJPAEntities.exception.NotEnoughStockException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id 
    @GeneratedValue
    @Column(name = "ITEM_ID", precision=4, scale=0)
    private BigDecimal id;

    private String name;        //이름
    private int price;          //가격
    private int stockQuantity;  //재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /* 재고 처리 관련 메서드 */
    /**
     * 재고 추가
     * @param quantity 재고 수량
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 감소
     * @param quantity 재고 수량
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Item [id=%s, name=%s, price=%s, stockQuantity=%s]", id, name, price, stockQuantity);
	}

}
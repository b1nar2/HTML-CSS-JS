/**
 * 
 */
package com.javateam.SpringBootJPAEntities.domain;

/**
 * 카테고리 : 상품과 "다:다" 관계를 맺는다.
 * 
 * 조인 테이블
 * 
 * 1. 데이터베이스에서 연관 관계(association)을 설계하는 방법
 * - 조인 컬럼 사용(외래 키)
 * - 조인 테이블(연결/링크 테이블) 사용(테이블 사용)
 * 
 * 2. 관계성
 * - 1:1 ; @OneToOne
 * - 1:다 ; @OneToMany
 * - 다:다 ; @ManytoMany
 * - 다:1 ; @ManyToOne
 * 
 * @author javateam
 *
 */
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id 
    @GeneratedValue
    @Column(name = "CATEGORY_ID", precision=4, scale=0)
    private BigDecimal id;

    private String name;

    @ManyToMany // 다:다
    @JoinTable(name = "CATEGORY_ITEM",
               joinColumns = @JoinColumn(name = "CATEGORY_ID"),
               inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<Item>();

    @ManyToOne(fetch = FetchType.LAZY) // 다:1
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent") // 1:다
    private List<Category> child = new ArrayList<Category>();

    /**
     * 연관관계 메서드
     */
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    public void addItem(Item item) {
        items.add(item);
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Category [id=%s, name=%s, items=%s, parent=%s, child=%s]", id, name, items, parent,
				child);
	}

}

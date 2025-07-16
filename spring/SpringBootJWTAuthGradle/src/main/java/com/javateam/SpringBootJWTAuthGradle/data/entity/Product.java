package com.javateam.SpringBootJWTAuthGradle.data.entity;

//import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
// Oracle
//@SequenceGenerator(
//	    name = "PRODUCT_SEQ_GENERATOR",
//	    sequenceName = "PRODUCT_SEQ",
//	    initialValue = 1,
//	    allocationSize = 1)
@Table(name="product_tbl")
public class Product {

    @Id
    @Column(name="num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Oracle
    // @Column(name="num", nullable=false, precision=20, scale=0) 
    // @GeneratedValue(strategy = GenerationType.SEQUENCE,
	//   				generator = "PRODUCT_SEQ_GENERATOR")
    private long number;
    // private BigDecimal number;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

}

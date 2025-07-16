package com.javateam.SpringBootJWTAuthGradle.data.repository;


import com.javateam.SpringBootJWTAuthGradle.data.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameLike(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameStartsWith(String name);

    List<Product> findByNameEndsWith(String name);

    List<Product> findByPriceGreaterThan(int price);

    List<Product> findByPriceLessThan(int price);

    List<Product> findByNameOrderByPriceAsc(String name);

}

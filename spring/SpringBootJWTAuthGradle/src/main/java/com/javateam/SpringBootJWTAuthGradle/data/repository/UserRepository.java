package com.javateam.SpringBootJWTAuthGradle.data.repository;

import com.javateam.SpringBootJWTAuthGradle.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserId(String userId);
    
    // 추가
    boolean existsByUserId(String userId);

}
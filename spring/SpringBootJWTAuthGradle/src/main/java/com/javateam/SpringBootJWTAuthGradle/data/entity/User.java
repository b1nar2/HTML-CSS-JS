package com.javateam.SpringBootJWTAuthGradle.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Oracle
//@SequenceGenerator(
//	    name = "USER_SEQ_GENERATOR",
//	    sequenceName = "USER_SEQ",
//	    initialValue = 1,
//	    allocationSize = 1)
@Table(name="user_tbl")
public class User implements UserDetails {

    private static final long serialVersionUID = 6014984039564979072L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Oracle
    // @Column(nullable=false, precision=20, scale=0)
    // @GeneratedValue // (strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE,
	// 				   generator = "USER_SEQ_GENERATOR")
    private long id;
    // private BigDecimal id;

    @Column(name="userId", nullable = false, unique = true)
    private String userId; // 회원 ID (JWT 토큰 내 정보)

    @JsonProperty(access = Access.WRITE_ONLY) // Json 결과로 출력하지 않을 데이터에 대해 해당 어노테이션 설정 값 추가
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * security 에서 사용하는 회원 구분 id
     *
     * @return uid
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userId;
    }

    /**
     * 계정이 만료되었는지 체크하는 로직
     * 이 예제에서는 사용하지 않으므로 true 값 return
     *
     * @return true
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겼는지 체크하는 로직
     * 이 예제에서는 사용하지 않으므로 true 값 return
     *
     * @return true
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 계정의 패스워드가 만료되었는지 체크하는 로직
     * 이 예제에서는 사용하지 않으므로 true 값 return
     *
     * @return true
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 사용가능한지 체크하는 로직
     * 이 예제에서는 사용하지 않으므로 true 값 return
     *
     * @return true
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
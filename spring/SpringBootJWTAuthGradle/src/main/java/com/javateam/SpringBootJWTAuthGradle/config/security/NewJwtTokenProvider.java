package com.javateam.SpringBootJWTAuthGradle.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * JWT 토큰을 생성하고 유효성을 검증하는 컴포넌트 클래스 JWT 는 여러 암호화 알고리즘을 제공하고 알고리즘과 비밀키를 가지고 토큰을 생성
 * <p>
 * claim 정보에는 토큰에 부가적으로 정보를 추가할 수 있음 claim 정보에 회원을 구분할 수 있는 값을 세팅하였다가 토큰이 들어오면 해당 값으로 회원을 구분하여 리소스
 * 제공
 * <p>
 * JWT 토큰에 expire time을 설정할 수 있음
 * 
 * 참고) JWT token 생성 사이트 : https://www.javainuse.com/jwtgenerator
 */
@Component
// @RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class NewJwtTokenProvider {
	
	@Autowired
	private UserDetailsService userDetailsService; // Spring Security 에서 제공하는 서비스 레이어
    
    private Key secretKey; // 중요) 이 부분에서 기존 버전(0.9.1)과 파편화
    
    // @Value("${springboot.jwt.secret}")
    // private String jwtKey; //  = "secretKey";
    
    public NewJwtTokenProvider(@Value("${springboot.jwt.newsecret}") String secretKey) {
    	
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }
    
    private final long tokenValidMillisecond = 1000L * 60 * 60; // 1시간 토큰 유효

    /**
     * SecretKey 에 대해 인코딩 수행
     */
    @PostConstruct
    protected void init() {
       
    	log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
    	
        log.info("secretKey : {}", this.secretKey);
        
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    // JWT 토큰 생성
    public String createToken(String userUid, List<String> roles) {
    	
        log.info("[createToken] 토큰 생성 시작");
        
        Claims claims = Jwts.claims().setSubject(userUid);
        claims.put("roles", roles);

        Date now = new Date();
        /*
        String token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
            .signWith(this.secretKey, SignatureAlgorithm.HS256) //  중요) 이 부분에서 기존 버전(0.9.1)과 파편화
            // 참고) 기존 코드
            // .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 세팅
            .compact();
		*/
        
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(this.secretKey, SignatureAlgorithm.HS256) //  중요) 이 부분에서 기존 버전(0.9.1)과 파편화
                // 참고) 기존 코드
                // .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 세팅
                .compact();
        log.info("[createToken] 토큰 생성 완료");
        
        return token;
    }

    // JWT 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
    	
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        
        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}",
        				userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰에서 회원 구별 정보 추출
    public String getUsername(String token) {
    	
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        
        // 중요) 이 부분에서 기존 버전(0.9.1)과 파편화
        // 참고) 기존 코드)
        // String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
        //    	             .getSubject();
        String info = Jwts.parserBuilder()
        				  .setSigningKey(secretKey)
		        		  .build()
		        		  .parseClaimsJws(token)
		        		  .getBody()
		        		  .getSubject();
        
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        
        return info;
    }

    /**
     * HTTP Request Header 에 설정된 토큰 값을 가져옴
     *
     * @param request Http Request Header
     * @return String type Token 값
     */
    public String resolveToken(HttpServletRequest request) {
    	
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        
        return request.getHeader("X-AUTH-TOKEN");
    }

    // JWT 토큰의 유효성 + 만료일 체크
    public boolean validateToken(String token) {
    	
        log.info("[validateToken] 토큰 유효 체크 시작");
        
        try {
        	
        	// 중요) 이 부분에서 기존 버전(0.9.1)과 파편화
        	// 참고) 기존 코드
        	// Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Jws<Claims> claims = Jwts.parserBuilder()
            						 .setSigningKey(secretKey)
            						 .build()
            						 .parseClaimsJws(token);
            
            log.info("[validateToken] 토큰 유효 체크 완료");
            
            return !claims.getBody().getExpiration().before(new Date());
            
        } catch (Exception e) {
            log.error("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
        
    } //
    
}
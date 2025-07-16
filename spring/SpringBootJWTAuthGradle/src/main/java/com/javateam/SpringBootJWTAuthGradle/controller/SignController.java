package com.javateam.SpringBootJWTAuthGradle.controller;

import com.javateam.SpringBootJWTAuthGradle.data.dto.SignInResultDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.SignUpResultDto;
import com.javateam.SpringBootJWTAuthGradle.service.SignService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-api")
@Slf4j
public class SignController {

    private final SignService signService;
    
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
    		@Parameter(name = "userId", description = "아이디", required = true) @RequestParam("userId") String userId, 
    		@Parameter(name = "password", description = "패쓰워드", required = true) @RequestParam("password") String password)
        throws RuntimeException {
    	
        log.info("[signIn] 로그인을 시도하고 있습니다. userId : {}, password : ****", userId);
        
        SignInResultDto signInResultDto = signService.signIn(userId, password);
        	
        if (signInResultDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. userId : {}, token : {}", userId,
                signInResultDto.getToken());
        }
        
        return signInResultDto;
    }

    // 참고) 관련 애너테이션(annotation) : https://springdoc.org/#migrating-from-springfox
    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(  
    		@Parameter(name = "userId", description = "아이디", required = true) @RequestParam("userId") String userId,
            @Parameter(name = "password", description = "패쓰워드", required = true) @RequestParam("password") String password,
            @Parameter(name = "name", description = "이름", required = true) @RequestParam("name") String name,
            @Parameter(name = "role", description = "권한", required = true) @RequestParam("role") String role) {
    	
    	log.info("--------------- signUp ----------------------");
    	
        log.info("[signUp] 회원가입을 수행합니다. userId : {}, password : ****, name : {}, role : {}", 
        			userId, name, role);
        SignUpResultDto signUpResultDto = signService.signUp(userId, password, name, role);

        log.info("[signUp] 회원가입을 완료했습니다. userId : {}", userId);
        
        return signUpResultDto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
    	
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        
        // 수정
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // 기본값
        String httpStatusCode = "400"; // 기본값

        log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());
        
        if (e.getMessage().equals("회원 정보가 존재하지 않습니다")) {
        	
        	httpStatus = HttpStatus.BAD_REQUEST;
        	httpStatusCode = "400";
       
        } else if (e.getMessage().equals("패쓰워드가 일치하지 않습니다")) {
        	
        	log.info(e.getMessage());
        	
        	httpStatus = HttpStatus.UNAUTHORIZED;
        	httpStatusCode = "401";
        }

        // 수정
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        // map.put("code", "400");
        map.put("code", httpStatusCode);
        // map.put("message", "에러 발생");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}
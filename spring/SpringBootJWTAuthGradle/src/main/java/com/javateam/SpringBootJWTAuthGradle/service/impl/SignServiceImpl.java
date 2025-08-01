package com.javateam.SpringBootJWTAuthGradle.service.impl;

import com.javateam.SpringBootJWTAuthGradle.common.CommonResponse;
//import com.javateam.SpringBootJWTAuthGradle.config.security.JwtTokenProvider;
import com.javateam.SpringBootJWTAuthGradle.config.security.NewJwtTokenProvider;
import com.javateam.SpringBootJWTAuthGradle.data.dto.SignInResultDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.SignUpResultDto;
import com.javateam.SpringBootJWTAuthGradle.data.entity.User;
import com.javateam.SpringBootJWTAuthGradle.data.repository.UserRepository;
import com.javateam.SpringBootJWTAuthGradle.service.SignService;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignServiceImpl implements SignService {

    public UserRepository userRepository;
    // public JwtTokenProvider jwtTokenProvider;
    public NewJwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    // public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
    public SignServiceImpl(UserRepository userRepository, NewJwtTokenProvider jwtTokenProvider,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String userId, String password, String name, String role) {
    	
        log.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        
        if (role.equalsIgnoreCase("admin")) {
        	
            user = User.builder()
                .userId(userId)
                .name(name)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build();
            
        } else {
        	
            user = User.builder()
                .userId(userId)
                .name(name)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        log.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        
        if (!savedUser.getName().isEmpty()) {
            log.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            log.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }
        
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String userId, String password) throws RuntimeException {
        log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        
        User user = userRepository.getByUserId(userId);
        
        // 수정 : javateacher
        if (user == null) {
        	throw new RuntimeException("회원 정보가 존재하지 않습니다");
        }
        
        log.info("[getSignInResult] userId : {}", userId);

        log.info("[getSignInResult] 패스워드 비교 수행");
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("패쓰워드가 일치하지 않습니다"); // 수정 : javateacher
        }
        
        log.info("[getSignInResult] 패스워드 일치");

        log.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
            .token(jwtTokenProvider.createToken(String.valueOf(user.getUserId()),
                user.getRoles()))
            .build();

        log.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    // 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}
package com.javateam.SpringBootJWTAuthGradle.service;


import com.javateam.SpringBootJWTAuthGradle.data.dto.SignInResultDto;
import com.javateam.SpringBootJWTAuthGradle.data.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String userId, String password, String name, String role);

    SignInResultDto signIn(String userId, String password) throws RuntimeException;
    
}
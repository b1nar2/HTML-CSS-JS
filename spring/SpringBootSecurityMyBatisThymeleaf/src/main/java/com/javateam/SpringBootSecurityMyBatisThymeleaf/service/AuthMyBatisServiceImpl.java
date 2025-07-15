/**
 * 
 */
package com.javateam.SpringBootSecurityMyBatisThymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.SpringBootSecurityMyBatisThymeleaf.dao.UserMapper;
import com.javateam.SpringBootSecurityMyBatisThymeleaf.domain.Users;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 * 
 */
@Service
@Slf4j
public class AuthMyBatisServiceImpl implements AuthMyBatisService {
	
	@Autowired
	private UserMapper dao;

	@Override
	public boolean hasUsername(String username) {

		log.info("hasUsername");
		
		return dao.hasUsername(username) == 1 ? true : false;
	} //

	@Override
	public void insertUsers(Users users, String role) {

		log.info("insertUsers");
		
		dao.insertUser(users);
		dao.insertUserRoles(users.getUsername(), role);
	} //

} //
package com.javateam.SpringJstlELDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.SpringJstlELDemo.dao.JobHistoryDAO;
import com.javateam.SpringJstlELDemo.domain.JobHistory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobHistoryService {
	
	@Autowired
	private JobHistoryDAO jobHistoryDAO;
	
	@Transactional(readOnly = true)
	public JobHistory getJobHistory(int employeeId) {
		
		log.info("service getJobHistory : {}", employeeId);		
		return jobHistoryDAO.getJobHistory(employeeId);
	}

}
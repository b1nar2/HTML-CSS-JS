package com.javateam.demoMyBatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.javateam.demoMyBatis.dao.TestDAO;
import com.javateam.demoMyBatis.domain.TestVO;
import com.javateam.demoMyBatis.service.TestMyBatisService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TestMyBatisServiceImpl implements TestMyBatisService {

	
	@Autowired
	@Qualifier("testDAO")
	private TestDAO testDAO;
	
	private final PlatformTransactionManager txManager;
	
	public TestMyBatisServiceImpl(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}
	
	@Autowired
	private TransactionTemplate txTemplate;
	
	
	@Transactional (readOnly = true)
	@Override
	public TestVO selectTestById(String id) {
		return testDAO.selectTestById(id);
	}

	
	
	@Override
	public boolean insertTest(TestVO testVO) {
		
//	    TransactionStatus txStatus =
//        txManager.getTransaction(new DefaultTransactionDefinition());
		
		// 위 코드와 같은 뜻
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus txStatus =
	            txManager.getTransaction(txDef);
		
	    
	    boolean result = false;
	    
	    try {
	    	
	    	testDAO.insertTest(testVO);
	    	txManager.commit(txStatus);
	    	result = true;
	    	
	    } catch (Exception e) {

	    	txManager.rollback(txStatus);
	    	result = false;
	    	log.error("TestMyBatisServiceImpl : " + e);
	    }
	    
		return result;
	}

	
	
	@Override
	public boolean insertTestTemplate(TestVO testVO) {
		
		boolean result = false;
		
		TransactionTemplate txTemplate = new TransactionTemplate(txManager);
		
		result = txTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				
				boolean result = false;
				
				try {
					testDAO.insertTest(testVO);
					result = true;
				} catch (Exception e) {
					result = false;
					status.setRollbackOnly();
				}
				
				return result;
			}

		});
		
		log.info("insertTestTemplate result : {}" , result);
		return result;
	}

	
	
	@Override
	public boolean insertTestTemplateLambda(TestVO testVO) {
		
		boolean result = false;
		
//		TransactionTemplate txTemplate = new TransactionTemplate(txManager);
		
		result = txTemplate.execute((status) -> {
				
				try {
					testDAO.insertTest(testVO);
					return true;
				} catch (Exception e) {
					status.setRollbackOnly();
					log.error("insertTestTemplateLambda error : " + e);
					return false;
				}
				
			}

		);
		
		return result;
	}

	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public boolean insertTestTransactional(TestVO testVO) {
		
		boolean result = false;
		
		try {
			testDAO.insertTest(testVO);
			result = true;
		} catch (Exception e) {
			// result = false;
			log.error("insertTestTransactional error : {} ", e);
		}
		
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public void insertTestTransactional2(TestVO testVO) {
		
		testDAO.insertTest(testVO);
	}
	
	
}
package com.paymybuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.dto.InternalTransactionExecutingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.proxy.InternalTransactionProxy;

/**
 *
 */
@Service
public class InternalTransactionService {

    private static final Logger logger = LogManager.getLogger("InternalTransactionService");
    
    @Autowired
    private InternalTransactionProxy internalTransactionProxy;
    
	public InternalTransactionService() {
        logger.info("internalTransactionService()");
	}
	
	public InternalTransactionExecutingDto executeInternalTransaction(InternalTransactionExecutingDto internalTransactionExecutingDto) {
        logger.info("executeInternalTransaction(" + internalTransactionExecutingDto + ")");	
		return internalTransactionProxy.executeInternalTransaction(internalTransactionExecutingDto);
	}
	
	public InternalTransactionRetrievingDto retrieveInternalTransactionList(InternalTransactionRetrievingDto internalTransactionRetrievingDto) {
        logger.info("retrieveInternalTransactionList(" + internalTransactionRetrievingDto + ")"); 
		return internalTransactionProxy.retrieveInternalTransactionList(internalTransactionRetrievingDto.getEmailAddress());
	}
}

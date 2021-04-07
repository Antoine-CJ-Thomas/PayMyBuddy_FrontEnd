package com.paymybuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.proxy.ExternalTransactionProxy;

/**
 * This class allows to interact with a ExternalTransactionProxy
 */
@Service
public class ExternalTransactionService {

    private static final Logger logger = LogManager.getLogger("ExternalTransactionService");
    
    @Autowired
    private ExternalTransactionProxy externalTransactionProxy;
    
	public ExternalTransactionService() {
        logger.info("ExternalTransactionService()");
	}
	
	public ExternalTransactionExecutingDto executeExternalTransaction(ExternalTransactionExecutingDto externalTransactionExecutingDto) {
        logger.info("executeExternalTransaction(" + externalTransactionExecutingDto + ")");	
		return externalTransactionProxy.executeExternalTransaction(externalTransactionExecutingDto);
	}
	
	public ExternalTransactionRetrievingDto retrieveExternalTransactionList(ExternalTransactionRetrievingDto externalTransactionRetrievingDto) {
        logger.info("retrieveExternalTransactionList(" + externalTransactionRetrievingDto + ")"); 
		return externalTransactionProxy.retrieveExternalTransactionList(externalTransactionRetrievingDto.getEmailAddress());
	}
}

package com.paymybuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.proxy.BankAccountProxy;

/**
 *
 */
@Service
public class BankAccountService {

    private static final Logger logger = LogManager.getLogger("BankAccountService");
    
    @Autowired
    private BankAccountProxy bankAccountProxy;
    
	public BankAccountService() {
        logger.info("BankAccountService()");
	}
	
	public BankAccountAddingDto addBankAccount(BankAccountAddingDto bankAccountAddingDto) {
        logger.info("addBankAccount(" + bankAccountAddingDto + ")");	
		return bankAccountProxy.addBankAccount(bankAccountAddingDto);
	}

	public BankAccountRemovingDto removeBankAccount(BankAccountRemovingDto bankAccountRemovingDto) {
        logger.info("removeBankAccount(" + bankAccountRemovingDto +")");
		return bankAccountProxy.removeBankAccount(bankAccountRemovingDto);
	}
	
	public BankAccountRetrievingDto retrieveBankAccountList(BankAccountRetrievingDto bankAccountRetrievingDto) {
        logger.info("retrieveBankAccountList(" + bankAccountRetrievingDto + ")"); 
		return bankAccountProxy.retrieveBankAccountList(bankAccountRetrievingDto);
	}
}

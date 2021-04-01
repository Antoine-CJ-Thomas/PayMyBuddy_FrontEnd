package com.paymybuddy.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.proxy.BankAccountProxy;
import com.paymybuddy.app.service.BankAccountService;

@SpringBootTest
class bankAccountServiceTest {

	@Autowired
	private BankAccountService bankAccountService;

	@Mock
	private BankAccountAddingDto bankAccountAddingDto;
	@Mock
	private BankAccountRemovingDto bankAccountRemovingDto;
	@Mock
	private BankAccountRetrievingDto bankAccountRetrievingDto;
	@Mock
    private BankAccountProxy bankAccountProxy;
    
	@BeforeEach
	void beforeEach() {

		//GIVEN
		bankAccountService = new BankAccountService();
		ReflectionTestUtils.setField(bankAccountService, "bankAccountProxy", bankAccountProxy);
	}
	
	@Test
	void test_addBankAccount() {
        
    	//WHEN		
		when(bankAccountProxy.addBankAccount(bankAccountAddingDto)).thenReturn(bankAccountAddingDto);
		
    	//THEN
        assertEquals(bankAccountAddingDto, bankAccountService.addBankAccount(bankAccountAddingDto));
	}
	
	@Test
	void test_removeBankAccount() {
        
    	//WHEN
		when(bankAccountProxy.removeBankAccount(bankAccountRemovingDto)).thenReturn(bankAccountRemovingDto);
	    
    	//THEN
        assertEquals(bankAccountRemovingDto, bankAccountService.removeBankAccount(bankAccountRemovingDto));
	}

	@Test
	void test_retrieveBankAccountList() {
        
    	//WHEN
		when(bankAccountRetrievingDto.getEmailAddress()).thenReturn("emailAddress");
		when(bankAccountProxy.retrieveBankAccountList(bankAccountRetrievingDto.getEmailAddress())).thenReturn(bankAccountRetrievingDto);
	    
    	//THEN
        assertEquals(bankAccountRetrievingDto, bankAccountService.retrieveBankAccountList(bankAccountRetrievingDto));
	}
}

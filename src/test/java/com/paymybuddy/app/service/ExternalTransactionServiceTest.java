package com.paymybuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.proxy.ExternalTransactionProxy;

@SpringBootTest
class ExternalTransactionServiceTest {

	private ExternalTransactionService externalTransactionService;

	@Mock
	private ExternalTransactionExecutingDto externalTransactionAddingDto;
	@Mock
	private ExternalTransactionRetrievingDto externalTransactionRetrievingDto;
	@Mock
    private ExternalTransactionProxy externalTransactionProxy;
    
	@BeforeEach
	void beforeEach() {

		//GIVEN
		externalTransactionService = new ExternalTransactionService();
		ReflectionTestUtils.setField(externalTransactionService, "ExternalTransactionProxy", externalTransactionProxy);
	}
	
	@Test
	void test_executeExternalTransaction() {
        
    	//WHEN		
		when(externalTransactionProxy.executeExternalTransaction(externalTransactionAddingDto)).thenReturn(externalTransactionAddingDto);
		
    	//THEN
        assertEquals(externalTransactionAddingDto, externalTransactionService.executeExternalTransaction(externalTransactionAddingDto));
	}

	@Test
	void test_retrieveExternalTransactionList() {
        
    	//WHEN
		when(externalTransactionRetrievingDto.getEmailAddress()).thenReturn("emailAddress");
		when(externalTransactionProxy.retrieveExternalTransactionList(externalTransactionRetrievingDto.getEmailAddress())).thenReturn(externalTransactionRetrievingDto);
	    
    	//THEN
        assertEquals(externalTransactionRetrievingDto, externalTransactionService.retrieveExternalTransactionList(externalTransactionRetrievingDto));
	}
}

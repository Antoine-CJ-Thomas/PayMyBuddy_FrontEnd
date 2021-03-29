package com.paymybuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.app.dto.InternalTransactionExecutingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.proxy.InternalTransactionProxy;

@SpringBootTest
class InternalTransactionServiceTest {

	private InternalTransactionService internalTransactionService;

	@Mock
	private InternalTransactionExecutingDto internalTransactionAddingDto;
	@Mock
	private InternalTransactionRetrievingDto internalTransactionRetrievingDto;
	@Mock
    private InternalTransactionProxy internalTransactionProxy;
    
	@BeforeEach
	void beforeEach() {

		//GIVEN
		internalTransactionService = new InternalTransactionService();
		ReflectionTestUtils.setField(internalTransactionService, "internalTransactionProxy", internalTransactionProxy);
	}
	
	@Test
	void test_executeInternalTransaction() {
        
    	//WHEN		
		when(internalTransactionProxy.executeInternalTransaction(internalTransactionAddingDto)).thenReturn(internalTransactionAddingDto);
		
    	//THEN
        assertEquals(internalTransactionAddingDto, internalTransactionService.executeInternalTransaction(internalTransactionAddingDto));
	}

	@Test
	void test_retrieveInternalTransactionList() {
        
    	//WHEN
		when(internalTransactionRetrievingDto.getEmailAddress()).thenReturn("emailAddress");
		when(internalTransactionProxy.retrieveInternalTransactionList(internalTransactionRetrievingDto.getEmailAddress())).thenReturn(internalTransactionRetrievingDto);
	    
    	//THEN
        assertEquals(internalTransactionRetrievingDto, internalTransactionService.retrieveInternalTransactionList(internalTransactionRetrievingDto));
	}
}

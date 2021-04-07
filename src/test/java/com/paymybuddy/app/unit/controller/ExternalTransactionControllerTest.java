package com.paymybuddy.app.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.controller.ExternalTransactionController;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.service.BankAccountService;
import com.paymybuddy.app.service.ExternalTransactionService;

@SpringBootTest
class ExternalTransactionControllerTest {

	private ExternalTransactionController externalTransactionController;

	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
	@Mock
	private ExternalTransactionExecutingDto externalTransactionExecutingDto;
	@Mock
	private ExternalTransactionRetrievingDto externalTransactionRetrievingDto;
	@Mock
	private BankAccountRetrievingDto bankAccountRetrievingDto;
	@Mock
	private ExternalTransactionService externalTransactionService;
	@Mock
	private BankAccountService bankAccountService;

	private String bankAccountName = "test account";
	private String description = "External transaction test";
	private float amount = 5.0f;
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		externalTransactionController = new ExternalTransactionController();
		
		ReflectionTestUtils.setField(externalTransactionController, "externalTransactionService", externalTransactionService);
		ReflectionTestUtils.setField(externalTransactionController, "bankAccountService", bankAccountService);
	}
	
	@Test
	void test_executeExternalTransaction() {
        
    	//WHEN
		when(externalTransactionService.executeExternalTransaction(any())).thenReturn(externalTransactionExecutingDto);
	    
    	//THEN
        assertEquals("redirect:/bank/transaction", externalTransactionController.executeExternalTransaction(model, redirectAttributes, bankAccountName, description, amount));
	}

	@Test
	void test_retrieveExternalTransactionList() {
        
    	//WHEN
		when(externalTransactionService.retrieveExternalTransactionList(any())).thenReturn(externalTransactionRetrievingDto);
		when(bankAccountService.retrieveBankAccountList(any())).thenReturn(bankAccountRetrievingDto);
	    
    	//THEN
        assertEquals("/bank_transaction.html", externalTransactionController.retrieveExternalTransactionList(model));
	}
}

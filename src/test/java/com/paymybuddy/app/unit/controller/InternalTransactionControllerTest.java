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

import com.paymybuddy.app.controller.InternalTransactionController;
import com.paymybuddy.app.dto.InternalTransactionExecutingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;
import com.paymybuddy.app.service.InternalTransactionService;
import com.paymybuddy.app.service.UserContactService;

@SpringBootTest
class InternalTransactionControllerTest {

	private InternalTransactionController internalTransactionController;

	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
	@Mock
	private InternalTransactionExecutingDto internalTransactionExecutingDto;
	@Mock
	private InternalTransactionRetrievingDto internalTransactionRetrievingDto;
	@Mock
	private UserContactRetrievingDto userContactRetrievingDto;
	@Mock
	private InternalTransactionService internalTransactionService;
	@Mock
	private UserContactService userContactService;
	
	private String contactEmailAddress = "contact.test@email";
	private String description = "Internal transaction test";
	private float amount = 5.0f;
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		internalTransactionController = new InternalTransactionController();
		
		ReflectionTestUtils.setField(internalTransactionController, "internalTransactionService", internalTransactionService);
		ReflectionTestUtils.setField(internalTransactionController, "userContactService", userContactService);
	}
	
	@Test
	void test_executeInternalTransaction() {
        
    	//WHEN
		when(internalTransactionService.executeInternalTransaction(any())).thenReturn(internalTransactionExecutingDto);
	    
    	//THEN
        assertEquals("redirect:/contact/transaction", internalTransactionController.executeInternalTransaction(model, redirectAttributes, contactEmailAddress, description, amount));
	}

	@Test
	void test_retrieveInternalTransactionList() {
        
    	//WHEN
		when(internalTransactionService.retrieveInternalTransactionList(any())).thenReturn(internalTransactionRetrievingDto);
		when(userContactService.retrieveUserContactList(any())).thenReturn(userContactRetrievingDto);
	    
    	//THEN
        assertEquals("/contact_transaction.html", internalTransactionController.retrieveInternalTransactionList(model));
	}
}

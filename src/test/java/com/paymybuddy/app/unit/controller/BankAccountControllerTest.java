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

import com.paymybuddy.app.controller.BankAccountController;
import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.service.BankAccountService;

@SpringBootTest
class BankAccountControllerTest {

	private BankAccountController BankAccountController;

	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
	@Mock
	private BankAccountAddingDto bankAccountAddingDto;
	@Mock
	private BankAccountRemovingDto bankAccountRemovingDto;
	@Mock
	private BankAccountRetrievingDto bankAccountRetrievingDto;
	@Mock
	private BankAccountService bankAccountService;

	private String bankAccountName = "test account";
	private String bankAccountNumber = "123456";
	private String bankAccountSwiftCode = "456789";
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		BankAccountController = new BankAccountController();
		
		ReflectionTestUtils.setField(BankAccountController, "bankAccountService", bankAccountService);
	}
	
	@Test
	void test_addBankAccount() {
        
    	//WHEN
		when(bankAccountService.addBankAccount(any())).thenReturn(bankAccountAddingDto);
	    
    	//THEN
        assertEquals("redirect:/bank", BankAccountController.addBankAccount(model, redirectAttributes, bankAccountName, bankAccountNumber, bankAccountSwiftCode));
	}
	
	@Test
	void test_removeBankAccount() {
        
    	//WHEN
		when(bankAccountService.removeBankAccount(any())).thenReturn(bankAccountRemovingDto);
	    
    	//THEN
        assertEquals("redirect:/bank", BankAccountController.removeBankAccount(model, redirectAttributes, bankAccountName));
	}

	@Test
	void test_retrieveBankAccountList() {
        
    	//WHEN
		when(bankAccountService.retrieveBankAccountList(any())).thenReturn(bankAccountRetrievingDto);
	    
    	//THEN
        assertEquals("/bank_list.html", BankAccountController.retrieveBankAccountList(model));
	}
}

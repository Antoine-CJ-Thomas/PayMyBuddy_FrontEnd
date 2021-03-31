package com.paymybuddy.app.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.controller.UserAccountController;
import com.paymybuddy.app.dto.UserAccountBalanceEditingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.service.ExternalTransactionService;
import com.paymybuddy.app.service.InternalTransactionService;
import com.paymybuddy.app.service.UserAccountService;

@SpringBootTest
class UserAccountControllerTest {

	private UserAccountController userAccountController;
	
	@Mock
    private UserAccountService userAccountService;
	@Mock
	private InternalTransactionService internalTransactionService;
	@Mock
	private ExternalTransactionService externalTransactionService;

	@Mock
	private UserAccountCreatingDto userAccountCreatingDto;
	@Mock
	private UserAccountDeletingDto userAccountDeletingDto;
	@Mock
	private UserAccountEditingDto userAccountEditingDto;
	@Mock
	private UserAccountRetrievingDto userAccountRetrievingDto;
	@Mock
	private UserAccountBalanceEditingDto userAccountBalanceEditingDto;
	
	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		userAccountController = new UserAccountController();
		
		ReflectionTestUtils.setField(userAccountController, "userAccountService", userAccountService);
		ReflectionTestUtils.setField(userAccountController, "internalTransactionService", internalTransactionService);
		ReflectionTestUtils.setField(userAccountController, "externalTransactionService", externalTransactionService);
	}
	
	@Test
	void test_registrationWebPage() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
        assertEquals("/registration.html", userAccountController.registrationWebPage(model));
	}
	
	@Test
	void test_createUserAccount() {

    	//GIVEN
		String emailAddress = "emailAddress";
		String password = "password";
		String firstName = "firstName";
		String lastName = "lastName";
        
    	//WHEN
		when(userAccountService.createUserAccount(any(UserAccountCreatingDto.class))).thenReturn(userAccountCreatingDto);
		when(userAccountCreatingDto.isDataValidated()).thenReturn(true);
	    
    	//THEN
	    assertEquals("redirect:/login", userAccountController.createUserAccount(model, redirectAttributes, emailAddress, password, firstName, lastName));
	}

	@Test
	void test_loginWebPage() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
        assertEquals("/login.html", userAccountController.loginWebPage(model, null));
	}

	@Test
	void test_homeWebPage() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
        assertEquals("/home.html", userAccountController.homeWebPage(model));
	}

	@Test
	void test_balanceWebPage() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
        assertEquals("/balance.html", userAccountController.balanceWebPage(model));
	}

	@Test
	@Disabled
	void test_editUserAccountBalance() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
	}

	@Test
	void test_profileWebPage() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
        assertEquals("/profile.html", userAccountController.profileWebPage(model));
	}

	@Test
	@Disabled
	void test_editUserAccount() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
	}

	@Test
	@Disabled
	void test_deleteUserAccount() {

    	//GIVEN
        
    	//WHEN
	    
    	//THEN
	}
}

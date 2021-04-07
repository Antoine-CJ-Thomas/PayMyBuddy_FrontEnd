package com.paymybuddy.app.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.controller.UserAccountController;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.dto.UserAccountBalanceEditingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.model.UserAccount;
import com.paymybuddy.app.service.ExternalTransactionService;
import com.paymybuddy.app.service.InternalTransactionService;
import com.paymybuddy.app.service.UserAccountService;

@SpringBootTest
class UserAccountControllerTest {

	@Autowired
	private UserAccountController userAccountController;

	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
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
	private InternalTransactionRetrievingDto internalTransactionRetrievingDto;
	@Mock
	private ExternalTransactionRetrievingDto externalTransactionRetrievingDto;
	@Mock
    private UserAccount userAccount;
	
	private String userEmailAddress = "user.test@email";
	private String userPassword = "123";
	private String userFirstName = "user";
	private String userLastName = "test";
	private float userBalance = 50.0f;
	
	private String cardNumber = "1234 1234 1234 1234";
	private String cardExpiration = "01/01";
	private String cardCryptogram = "123";
	private float payementAmount = 10.0f;

	private String error = "error";
	private String message = "message";
    
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
	    
    	//THEN
        assertEquals("/registration.html", userAccountController.registrationWebPage(model));
	}
	
	@Test
	void test_createUserAccount() {
        
    	//WHEN
		when(userAccountService.createUserAccount(any())).thenReturn(userAccountCreatingDto);
	    
    	//THEN
	    assertEquals("redirect:/registration", userAccountController.createUserAccount(model, redirectAttributes, userEmailAddress, userPassword, userFirstName, userLastName));
	}

	@Test
	void test_loginWebPage() {
	    
    	//THEN
        assertEquals("/login.html", userAccountController.loginWebPage(model, error));
	}

	@Test
	void test_homeWebPage() {
        
    	//WHEN
		when(userAccount.getBalanceAmount()).thenReturn(userBalance);
		when(userAccountRetrievingDto.getUserAccount()).thenReturn(userAccount);
		when(userAccountService.retrieveUserAccount(any())).thenReturn(userAccountRetrievingDto);
		when(internalTransactionService.retrieveInternalTransactionList(any())).thenReturn(internalTransactionRetrievingDto);
		when(externalTransactionService.retrieveExternalTransactionList(any())).thenReturn(externalTransactionRetrievingDto);
	    
    	//THEN
        assertEquals("/home.html", userAccountController.homeWebPage(model));
	}

	@Test
	void test_balanceWebPage() {
        
    	//WHEN
		when(userAccount.getBalanceAmount()).thenReturn(userBalance);
		when(userAccountRetrievingDto.getUserAccount()).thenReturn(userAccount);
		when(userAccountService.retrieveUserAccount(any())).thenReturn(userAccountRetrievingDto);
	    
    	//THEN
        assertEquals("/balance.html", userAccountController.balanceWebPage(model));
	}

	@Test
	void test_editUserAccountBalance() {
        
    	//WHEN
		when(userAccountBalanceEditingDto.getMessage()).thenReturn(message);
		when(userAccountService.editUserAccountBalance(any())).thenReturn(userAccountBalanceEditingDto);
	    
    	//THEN
        assertEquals("redirect:/balance", userAccountController.editUserAccountBalance(model, redirectAttributes, cardNumber, cardExpiration, cardCryptogram, payementAmount));
	}

	@Test
	void test_profileWebPage() {
        
    	//WHEN
		when(userAccount.getEmailAddress()).thenReturn(userEmailAddress);
		when(userAccount.getFirstName()).thenReturn(userFirstName);
		when(userAccount.getLastName()).thenReturn(userLastName);
		when(userAccountRetrievingDto.getUserAccount()).thenReturn(userAccount);
		when(userAccountService.retrieveUserAccount(any(UserAccountRetrievingDto.class))).thenReturn(userAccountRetrievingDto);
	    
    	//THEN
        assertEquals("/profile.html", userAccountController.profileWebPage(model));
	}

	@Test
	void test_editUserAccount() {
        
    	//WHEN
		when(userAccountEditingDto.getMessage()).thenReturn(message);
		when(userAccountService.editUserAccount(any())).thenReturn(userAccountEditingDto);
	    
    	//THEN
        assertEquals("redirect:/profile", userAccountController.editUserAccount(model, redirectAttributes, userPassword, userFirstName, userLastName));
	}

	@Test
	void test_deleteUserAccount() {
        
    	//WHEN
		when(userAccount.getPassword()).thenReturn(userPassword);
		when(userAccountRetrievingDto.getUserAccount()).thenReturn(userAccount);
		when(userAccountService.retrieveUserAccount(any())).thenReturn(userAccountRetrievingDto);
	    
    	//THEN
        assertEquals("redirect:/profile", userAccountController.deleteUserAccount(model, redirectAttributes, userPassword));
	}
}

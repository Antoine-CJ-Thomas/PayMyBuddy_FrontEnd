package com.paymybuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.proxy.UserAccountProxy;

@SpringBootTest
class UserAccountServiceTest {

	private UserAccountService userAccountService;

	@Mock
	private UserAccountCreatingDto userAccountCreatingDto;
	@Mock
	private UserAccountDeletingDto userAccountDeletingDto;
	@Mock
	private UserAccountEditingDto userAccountEditingDto;
	@Mock
	private UserAccountRetrievingDto userAccountRetrievingDto;
	@Mock
    private UserAccountProxy userAccountProxy;
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		userAccountService = new UserAccountService();
		ReflectionTestUtils.setField(userAccountService, "userAccountProxy", userAccountProxy);
	}
	
	@Test
	void test_createUserAccount() {
        
    	//WHEN		
		when(userAccountProxy.createUserAccount(userAccountCreatingDto)).thenReturn(userAccountCreatingDto);
		
    	//THEN
        assertEquals(userAccountCreatingDto, userAccountService.createUserAccount(userAccountCreatingDto));
	}
	
	@Test
	void test_deleteUserAccount() {
        
    	//WHEN		
		when(userAccountProxy.deleteUserAccount(userAccountDeletingDto)).thenReturn(userAccountDeletingDto);
		
    	//THEN
        assertEquals(userAccountDeletingDto, userAccountService.deleteUserAccount(userAccountDeletingDto));
	}

	@Test
	void test_editUserAccount() {
        
    	//WHEN		
		when(userAccountProxy.editUserAccount(userAccountEditingDto)).thenReturn(userAccountEditingDto);
		
    	//THEN
        assertEquals(userAccountEditingDto, userAccountService.editUserAccount(userAccountEditingDto));
	}

	@Test
	void test_retrieveUserAccount() {
        
    	//WHEN		
		when(userAccountRetrievingDto.getEmailAddress()).thenReturn("emailAddress");
		when(userAccountProxy.retrieveUserAccount(userAccountRetrievingDto.getEmailAddress())).thenReturn(userAccountRetrievingDto);
		
    	//THEN
        assertEquals(userAccountRetrievingDto, userAccountService.retrieveUserAccount(userAccountRetrievingDto));
	}
}

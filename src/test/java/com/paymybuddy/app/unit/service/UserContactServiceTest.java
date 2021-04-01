package com.paymybuddy.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.paymybuddy.app.dto.UserContactAddingDto;
import com.paymybuddy.app.dto.UserContactRemovingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;
import com.paymybuddy.app.proxy.UserContactProxy;
import com.paymybuddy.app.service.UserContactService;

@SpringBootTest
class UserContactServiceTest {

	private UserContactService userContactService;

	@Mock
	private UserContactAddingDto userContactAddingDto;
	@Mock
	private UserContactRemovingDto userContactRemovingDto;
	@Mock
	private UserContactRetrievingDto userContactRetrievingDto;
	@Mock
    private UserContactProxy userContactProxy;
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		userContactService = new UserContactService();
		ReflectionTestUtils.setField(userContactService, "userContactProxy", userContactProxy);
	}
	
	@Test
	void test_createUserContact() {
        
    	//WHEN		
		when(userContactProxy.addUserContact(userContactAddingDto)).thenReturn(userContactAddingDto);
		
    	//THEN
        assertEquals(userContactAddingDto, userContactService.addUserContact(userContactAddingDto));
	}
	
	@Test
	void test_deleteUserContact() {
        
    	//WHEN		
		when(userContactProxy.removeUserContact(userContactRemovingDto)).thenReturn(userContactRemovingDto);
		
    	//THEN
        assertEquals(userContactRemovingDto, userContactService.removeUserContact(userContactRemovingDto));
	}

	@Test
	void test_retrieveUserContact() {
        
    	//WHEN		
		when(userContactRetrievingDto.getEmailAddress()).thenReturn("emailAddress");
		when(userContactProxy.retrieveUserContactList(userContactRetrievingDto.getEmailAddress())).thenReturn(userContactRetrievingDto);
		
    	//THEN
        assertEquals(userContactRetrievingDto, userContactService.retrieveUserContactList(userContactRetrievingDto));
	}
}

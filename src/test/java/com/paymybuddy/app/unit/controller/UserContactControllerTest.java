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

import com.paymybuddy.app.controller.UserContactController;
import com.paymybuddy.app.dto.UserContactAddingDto;
import com.paymybuddy.app.dto.UserContactRemovingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;
import com.paymybuddy.app.service.UserContactService;

@SpringBootTest
class UserContactControllerTest {

	private UserContactController userContactController;

	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;
	@Mock
	private UserContactAddingDto userContactAddingDto;
	@Mock
	private UserContactRemovingDto userContactRemovingDto;
	@Mock
	private UserContactRetrievingDto userContactRetrievingDto;
	@Mock
	private UserContactService userContactService;
	
	private String contactEmailAddress = "contact.test@email";
    
	@BeforeEach
	void beforeEach() {

    	//GIVEN
		userContactController = new UserContactController();
		
		ReflectionTestUtils.setField(userContactController, "userContactService", userContactService);
	}
	
	@Test
	void test_addUserContact() {
        
    	//WHEN
		when(userContactService.addUserContact(any())).thenReturn(userContactAddingDto);
	    
    	//THEN
        assertEquals("redirect:/contact", userContactController.addUserContact(model, redirectAttributes, contactEmailAddress));
	}
	
	@Test
	void test_removeUserContact() {
        
    	//WHEN
		when(userContactService.removeUserContact(any())).thenReturn(userContactRemovingDto);
	    
    	//THEN
        assertEquals("redirect:/contact", userContactController.removeUserContact(model, redirectAttributes, contactEmailAddress));
	}

	@Test
	void test_retrieveUserContactList() {
        
    	//WHEN
		when(userContactService.retrieveUserContactList(any())).thenReturn(userContactRetrievingDto);
	    
    	//THEN
        assertEquals("/contact_list.html", userContactController.retrieveUserContactList(model));
	}
}

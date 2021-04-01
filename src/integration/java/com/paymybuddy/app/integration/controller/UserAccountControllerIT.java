package com.paymybuddy.app.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.App;
import com.paymybuddy.app.controller.UserAccountController;

@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = {App.class})
class UserAccountControllerIT {
	
	private MockMvc mockMvc;
	private MvcResult mvcResult;
	   
	@Autowired
   	private WebApplicationContext webApplicationContext;

	private UserAccountController userAccountController;
	
	private String userEmailAddress = "user.test@email";
	private String userPassword = "123";
	private String userFirstName = "user";
	private String userLastName = "test";
	
	private String userNewPassword = "456";
	
	private String cardNumber = "1234 1234 1234 1234";
	private String cardExpiration = "01/01";
	private String cardCryptogram = "123";
	private float payementAmount = 10.0f;
	
	private Model model;
	private RedirectAttributes redirectAttributes;
	
	@BeforeEach
	private void beforeEach() {

    	//GIVEN
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
    @Order(1)
	void test_registrationWebPage() throws Exception {
		
    	//WHEN
		mvcResult = mockMvc.perform(get("/registration")).andReturn();

    	//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
    @Order(2)
	void test_createUserAccount() throws Exception {
		
	}
	
	@Test
    @Order(3)
	void test_loginWebPage() throws Exception {
		
    	//WHEN
		mvcResult = mockMvc.perform(get("/login")).andReturn();

    	//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
    @Order(4)
	void test_homeWebPage() throws Exception {
		
    	//WHEN
		mvcResult = mockMvc.perform(get("/home")).andReturn();

    	//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
    @Order(5)
	@Disabled
	void test_balanceWebPage() throws Exception {
		
	}

	@Test
    @Order(6)
	@Disabled
	void test_editUserAccountBalance() throws Exception {
		
	}
	
	@Test
    @Order(7)
	void test_profileWebPage() throws Exception {
		
    	//WHEN
		mvcResult = mockMvc.perform(get("/profile")).andReturn();

    	//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
    @Order(8)
	@Disabled
	void test_editUserAccount() throws Exception {
		
	}
	
	@Test
    @Order(9)
	@Disabled
	void test_deleteUserAccount() throws Exception {
		
	}
}

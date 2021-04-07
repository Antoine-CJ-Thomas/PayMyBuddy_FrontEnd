package com.paymybuddy.app.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserAccountControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private MvcResult mvcResult;

	@BeforeEach
	private void beforeEach() {

		//GIVEN
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void test_registrationWebPage() throws Exception {

		//WHEN
		mvcResult = mockMvc.perform(get("/registration")).andReturn();

		//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	void test_loginWebPage() throws Exception {

		//WHEN
		mvcResult = mockMvc.perform(get("/login")).andReturn();

		//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
    @WithMockUser
	void test_homeWebPage() throws Exception {

		//WHEN        
		mvcResult = mockMvc.perform(get("/home")).andReturn();

		//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
    @WithMockUser
	void test_balanceWebPage() throws Exception {

		//WHEN
		mvcResult = mockMvc.perform(get("/balance")).andReturn();

		//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());

	}

	@Test
    @WithMockUser
	void test_profileWebPage() throws Exception {

		//WHEN
		mvcResult = mockMvc.perform(get("/profile")).andReturn();

		//THEN
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
}

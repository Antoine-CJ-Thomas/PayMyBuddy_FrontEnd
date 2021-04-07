package com.paymybuddy.app.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.app.model.InternalTransaction;
import com.paymybuddy.app.model.UserContact;

@SpringBootTest
class InternalTransactionTest {

	private InternalTransaction internalTransaction;
	
	@Mock
	private UserContact userContact;
    
	@BeforeEach
	void beforeEach() {

		internalTransaction = new InternalTransaction(null, null, null, 0.0f);
	}
    
	@Test
	void test_setAndGetUserContact() {

    	//GIVEN
        
    	//WHEN
		internalTransaction.setUserContact(userContact);
    	
    	//THEN
        assertEquals(userContact, internalTransaction.getUserContact());
	}
}

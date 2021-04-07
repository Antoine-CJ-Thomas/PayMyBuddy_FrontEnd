package com.paymybuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.dto.UserContactAddingDto;
import com.paymybuddy.app.dto.UserContactRemovingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;
import com.paymybuddy.app.proxy.UserContactProxy;

/**
 * This class allows to interact with a UserContactProxy
 */
@Service
public class UserContactService {

    private static final Logger logger = LogManager.getLogger("UserContactService");
    
    @Autowired
    private UserContactProxy userContactProxy;
    
	public UserContactService() {
        logger.info("UserContactService()");
	}
	
	public UserContactAddingDto addUserContact(UserContactAddingDto userContactAddingDto) {
        logger.info("addUserContact(" + userContactAddingDto + ")");	
		return userContactProxy.addUserContact(userContactAddingDto);
	}

	public UserContactRemovingDto removeUserContact(UserContactRemovingDto userContactRemovingDto) {
        logger.info("removeUserContact(" + userContactRemovingDto +")");
		return userContactProxy.removeUserContact(userContactRemovingDto);
	}
	
	public UserContactRetrievingDto retrieveUserContactList(UserContactRetrievingDto userContactRetrievingDto) {
        logger.info("retrieveUserContactList(" + userContactRetrievingDto + ")"); 
		return userContactProxy.retrieveUserContactList(userContactRetrievingDto.getEmailAddress());
	}
}

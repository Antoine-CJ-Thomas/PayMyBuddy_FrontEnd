package com.paymybuddy.app.service;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountLoginDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.model.UserAccount;
import com.paymybuddy.app.proxy.UserAccountProxy;

/**
 *
 */
@Service
public class UserAccountService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger("UserAccountService");
    
    @Autowired
    private UserAccountProxy userAccountProxy;
    
	public UserAccountService() {
        logger.info("UserAccountService()");
	}
	
	public UserAccountCreatingDto createUserAccount(UserAccountCreatingDto userAccountCreatingDto) {
        logger.info("createUserAccount(" + userAccountCreatingDto + ")");	
		return userAccountProxy.createUserAccount(userAccountCreatingDto);
	}

	public UserAccountDeletingDto deleteUserAccount(UserAccountDeletingDto userAccountDeletingDto) {
        logger.info("deleteUserAccount(" + userAccountDeletingDto +")");
		return userAccountProxy.deleteUserAccount(userAccountDeletingDto);
	}

	public UserAccountEditingDto editUserAccount(UserAccountEditingDto userAccountEditingDto) {
        logger.info("editUserAccount(" + userAccountEditingDto + ")");
		return userAccountProxy.editUserAccount(userAccountEditingDto);
	}

	public UserAccountLoginDto loginUserAccount(UserAccountLoginDto userAccountLoginDto) {
        logger.info("loginUserAccount(" + userAccountLoginDto +")");
		return userAccountProxy.loginUserAccount(userAccountLoginDto);
	}

	public UserAccountRetrievingDto retrieveUserAccount(UserAccountRetrievingDto userAccountRetrievingDto) {
        logger.info("retrieveUserAccount(" + userAccountRetrievingDto + ")"); 
		return userAccountProxy.retrieveUserAccount(userAccountRetrievingDto.getEmailAddress());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername(" + username + ")"); 
		
        Objects.requireNonNull(username);
        
        UserAccount userAccount = userAccountProxy.retrieveUserAccount(username).getUserAccount();////////////////////////////////////////////
        
        userAccount.setPassword(BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt(12)));
                
        return userAccount;
	}
}

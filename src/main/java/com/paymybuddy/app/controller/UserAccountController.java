package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountLoginDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.model.UserAccount;
import com.paymybuddy.app.service.UserAccountService;

@Controller
public class UserAccountController {

    private static final Logger logger = LogManager.getLogger("UserAccountController");

    private String pageName;
    
    @Autowired
	private UserAccountService userAccountService;
	
	
	public UserAccountController() {
        logger.info("UserAccountController()");
	}
	
    @GetMapping("/registration")
    public String registrationWebPage(Model model) {
        logger.info("registrationWebPage()");        
        return "registration.html";
    }
	
    @PostMapping("/registration")
    public String createUserAccount(Model model, 
    		@RequestParam(value = "email_address") String emailAddress, 
    		@RequestParam(value = "password") String password, 
    		@RequestParam(value = "first_name") String firstName, 
    		@RequestParam(value = "last_name") String lastName) {
    	
        logger.info("createUserAccount()");        
        	
        UserAccountCreatingDto UserAccountCreatingDto = userAccountService.createUserAccount(new UserAccountCreatingDto(emailAddress, password, firstName, lastName));
        
		if (UserAccountCreatingDto.isDataValidated()) {

			pageName = "index.html";
		}
		
		else {
			
		    model.addAttribute("message_label", UserAccountCreatingDto.getMessage());
			pageName = "registration.html";
		}
        	
        return pageName;
    }
	
    @GetMapping("/login")
    public String loginWebPage(Model model) {
        logger.info("loginWebPage()");        
        return "index.html";
    }
	
    @PostMapping("/login")
    public String loginUserAccount(Model model, 
    		@RequestParam(value = "email_address") String emailAddress, 
    		@RequestParam(value = "password") String password) {
    	
        logger.info("loginUserAccount()");
        
        UserAccountLoginDto userAccountLoginDto = userAccountService.loginUserAccount(new UserAccountLoginDto(emailAddress, password));
                
        if (userAccountLoginDto.isDataValidated()) {
            
        	UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(emailAddress));
        	
            model.addAttribute("balance", userAccountRetrievingDto.getUserAccount().getBalanceAmount());
        	pageName = "home.html";
        }
        
        else {
        	
            model.addAttribute("message_label", userAccountLoginDto.getMessage());
        	pageName = "index.html";
        }
        
        return pageName;
    }

	@DeleteMapping("/profile")
	public String deleteUserAccount(@RequestBody UserAccountDeletingDto userAccountDeletingDto) {
        logger.info("deleteUserAccount()");
		return "profile.html";
	}

	@PostMapping("/profile")/////////////////////////////////POST NOT PUT
	public String editUserAccount(Model model,
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "first_name") String first_name,
    		@RequestParam(value = "last_name") String last_name) {
		
        logger.info("editUserAccount()");

        model.addAttribute("email_address", "antoine.thomas@email TEST");
        model.addAttribute("first_name", first_name);
        model.addAttribute("last_name", last_name);
        
        return "profile.html";
	}
	
    @GetMapping("/profile")
    public String profileWebPage(Model model) {
        logger.info("profileWebPage()");/*
    	
    	UserAccount userAccount = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto("emailAddress")).getUserAccount();

        model.addAttribute("email_address", userAccount.getEmailAddress());
        model.addAttribute("first_name", userAccount.getFirstName());
        model.addAttribute("last_name", userAccount.getLastName());
*/           
        
        model.addAttribute("email_address", "antoine.thomas@email TEST");
        model.addAttribute("first_name", "antoine TEST");
        model.addAttribute("last_name", "thomas TEST");
        
        return "profile.html";
    }
}

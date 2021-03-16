package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountLoginDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
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
	
    @GetMapping("{/, /login}")
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
        	
            model.addAttribute("login_message", userAccountLoginDto.getMessage());
        	pageName = "index.html";
        }
        
        return pageName;
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
        	
        UserAccountCreatingDto userAccountCreatingDto = userAccountService.createUserAccount(new UserAccountCreatingDto(emailAddress, password, firstName, lastName));
        
		if (userAccountCreatingDto.isDataValidated()) {

			pageName = "index.html";
		}
		
		else {
			
		    model.addAttribute("registration_message", userAccountCreatingDto.getMessage());
			pageName = "registration.html";
		}
        	
        return pageName;
    }
	
    @GetMapping("/profile")
    public String retrieveUserAccount(Model model) {
        logger.info("retrieveUserAccount()");
    	
        UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto("test"));

        model.addAttribute("email_address", userAccountRetrievingDto.getUserAccount().getEmailAddress());
        model.addAttribute("first_name", userAccountRetrievingDto.getUserAccount().getFirstName());
        model.addAttribute("last_name", userAccountRetrievingDto.getUserAccount().getLastName());
        
        return "profile.html";
    }

	@PostMapping("/profile")/////////////////////////////////POST NOT PUT
	public String editUserAccount(Model model,
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "first_name") String first_name,
    		@RequestParam(value = "last_name") String last_name) {
		
        logger.info("editUserAccount()");        
   	
        UserAccountEditingDto userAccountEditingDto = userAccountService.editUserAccount(new UserAccountEditingDto("test", password, first_name, last_name));
	    
		if (userAccountEditingDto.isDataValidated()) {
	    	
	        UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto("test"));

	        model.addAttribute("email_address", userAccountRetrievingDto.getUserAccount().getEmailAddress());
	        model.addAttribute("first_name", userAccountRetrievingDto.getUserAccount().getFirstName());
	        model.addAttribute("last_name", userAccountRetrievingDto.getUserAccount().getLastName());
	
			pageName = "profile.html";
		}
		
		else {
			
		    model.addAttribute("edition_message", userAccountEditingDto.getMessage());
			pageName = "profile.html";
		}
        
        return pageName;
	}
	
    @PostMapping("/delete")/////////////////////////////////POST NOT DELETE
    public String deleteUserAccount(Model model,
    		@RequestParam(value = "password") String password) {
    	
        logger.info("deleteUserAccount()");        
   	
	    UserAccountDeletingDto userAccountDeletingDto = userAccountService.deleteUserAccount(new UserAccountDeletingDto("test", password));
	    
		if (userAccountDeletingDto.isDataValidated()) {
	
			pageName = "index.html";
		}
		
		else {
			
		    model.addAttribute("deletion_message", userAccountDeletingDto.getMessage());
			pageName = "profile.html";
		}
        
        return pageName;
    }
}

package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
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
	
    @GetMapping(value = "/registration")
    public String registrationWebPage(Model model) {
        logger.info("registrationWebPage()");               
        return "/registration.html";
    }

    @PostMapping(value = "/account/create")
    public String createUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "email_address") String emailAddress, 
    		@RequestParam(value = "password") String password, 
    		@RequestParam(value = "first_name") String firstName, 
    		@RequestParam(value = "last_name") String lastName) {
    	
        logger.info("createUserAccount()");        
        	
        UserAccountCreatingDto userAccountCreatingDto = userAccountService.createUserAccount(new UserAccountCreatingDto(emailAddress, password, firstName, lastName));
                
		if (userAccountCreatingDto.isDataValidated()) {
	        
        	pageName = "/login";
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("registration_message", userAccountCreatingDto.getMessage());
        	pageName = "/registration";
		}
		
		return ("redirect:" + pageName);
    }
	
    @GetMapping(value = {"/", "/login"})
    public String loginWebPage(Model model) {
        logger.info("loginWebPage()");
        return "/login.html";
    }
	
    @GetMapping(value = "/home")
    public String homeWebPage(Model model) {
        logger.info("homeWebPage()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
                
    	UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));
    	
        model.addAttribute("balance", userAccountRetrievingDto.getUserAccount().getBalanceAmount());
        
        return "/home.html";
    }
	
    @GetMapping(value = "/profile")
    public String profileWebPage(Model model) {
        logger.info("profileWebPage()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
    	
        UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));

        model.addAttribute("email_address", userAccountRetrievingDto.getUserAccount().getEmailAddress());
        model.addAttribute("first_name", userAccountRetrievingDto.getUserAccount().getFirstName());
        model.addAttribute("last_name", userAccountRetrievingDto.getUserAccount().getLastName());

        return "/profile.html";
    }

	@PostMapping(value = "/account/edit")
	public String editUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "first_name") String first_name,
    		@RequestParam(value = "last_name") String last_name) {
		
        logger.info("editUserAccount()");        
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
   	
        UserAccountEditingDto userAccountEditingDto = userAccountService.editUserAccount(new UserAccountEditingDto(userEmailAddress, password, first_name, last_name));
	    
		if (userAccountEditingDto.isDataValidated()) {
			
			pageName = "/profile";
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("edition_message", userAccountEditingDto.getMessage());
			pageName = "/profile";
		}

		return ("redirect:" + pageName);
	}
	
    @PostMapping(value = "/account/delete")
    public String deleteUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "password") String password) {
    	
        logger.info("deleteUserAccount()");        
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
   	
	    UserAccountDeletingDto userAccountDeletingDto = userAccountService.deleteUserAccount(new UserAccountDeletingDto(userEmailAddress, password));
	    
		if (userAccountDeletingDto.isDataValidated()) {
	
			pageName = "/login";
		}
		
		else {

			redirectAttributes.addFlashAttribute("deletion_message", userAccountDeletingDto.getMessage());
			pageName = "/profile";
		}
		
		return ("redirect:" + pageName);
    }
}

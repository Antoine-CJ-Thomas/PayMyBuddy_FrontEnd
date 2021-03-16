package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.UserContactAddingDto;
import com.paymybuddy.app.dto.UserContactRemovingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;
import com.paymybuddy.app.service.UserContactService;

@Controller
public class UserContactController {

    private static final Logger logger = LogManager.getLogger("UserContactController");

    @Autowired
	private UserContactService userContactService;
	
	
	public UserContactController() {
        logger.info("UserContactController()");
	}
	
    @GetMapping("/contact/list")
    public String retrieveUserContactList(Model model) {
        logger.info("retrieveUserContactList()");
    	
        UserContactRetrievingDto userContactRetrievingDto = userContactService.retrieveUserContactList(new UserContactRetrievingDto("test"));

        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
        
        return "contact_list.html";
    }
	
    @PostMapping("/contact/add")
    public String addUserContact(Model model, 
    		@RequestParam(value = "email_address") String emailAddress) {
    	
        logger.info("addUserContact()");        
        	
        UserContactAddingDto userContactAddingDto = userContactService.addUserContact(new UserContactAddingDto("test", emailAddress));
        UserContactRetrievingDto userContactRetrievingDto = userContactService.retrieveUserContactList(new UserContactRetrievingDto("test"));
        
		if (userContactAddingDto.isDataValidated()) {

	        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
		}
		
		else {

	        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
		    model.addAttribute("adding_message", userContactAddingDto.getMessage());
		}

        return "contact_list.html";
    }
	
    @PostMapping("/contact/delete")/////////////////////////////////POST NOT DELETE
    public String removeUserContact(Model model,
    		@RequestParam(value = "email_address") String emailAddress) {
    	
        logger.info("removeUserContact()");        
   	
        UserContactRemovingDto userContactRemovingDto = userContactService.removeUserContact(new UserContactRemovingDto("test", emailAddress));
        UserContactRetrievingDto userContactRetrievingDto = userContactService.retrieveUserContactList(new UserContactRetrievingDto("test"));
        
		if (userContactRemovingDto.isDataValidated()) {

	        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
		}
		
		else {

	        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
		    model.addAttribute("removing_message", userContactRemovingDto.getMessage());
		}

        return "contact_list.html";
    }
}

package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
    @GetMapping("/contact")
    public String retrieveUserContactList(Model model) {
        logger.info("retrieveUserContactList()");
    	
        UserContactRetrievingDto userContactRetrievingDto = userContactService.retrieveUserContactList(new UserContactRetrievingDto("antoine.thomas@email"));

        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
        
        return "/contact_list.html";
    }
	
    @PostMapping("/contact/add")
    public ModelAndView addUserContact(Model model, 
    		@RequestParam(value = "email_address") String emailAddress) {
    	
        logger.info("addUserContact()");        
        	
        UserContactAddingDto userContactAddingDto = userContactService.addUserContact(new UserContactAddingDto("antoine.thomas@email", emailAddress));
        
		if (userContactAddingDto.isDataValidated() == false) {

		    model.addAttribute("adding_message", userContactAddingDto.getMessage());
		}

		return new ModelAndView("redirect:/contact_list");
    }
	
    @PostMapping("/contact/delete")/////////////////////////////////POST NOT DELETE
    public ModelAndView removeUserContact(Model model,
    		@RequestParam(value = "email_address") String emailAddress) {
    	
        logger.info("removeUserContact()");        
   	
        UserContactRemovingDto userContactRemovingDto = userContactService.removeUserContact(new UserContactRemovingDto("antoine.thomas@email", emailAddress));
        
		if (userContactRemovingDto.isDataValidated() == false) {

		    model.addAttribute("removing_message", userContactRemovingDto.getMessage());
		}

		return new ModelAndView("redirect:/contact_list");
    }
}

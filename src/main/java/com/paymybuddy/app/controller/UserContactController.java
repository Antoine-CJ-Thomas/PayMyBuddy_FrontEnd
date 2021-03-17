package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addUserContact(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "contact_email_address") String contactEmailAddress) {
    	
        logger.info("addUserContact()");        

       
        UserContactAddingDto userContactAddingDto = userContactService.addUserContact(new UserContactAddingDto("antoine.thomas@email", contactEmailAddress));
        
		if (userContactAddingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("adding_message", userContactAddingDto.getMessage());
		}
		
		return ("redirect:/contact");
    }
	
    @PostMapping("/contact/remove")
    public String removeUserContact(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "contact_email_address") String contactEmailAddress) {
    	
        logger.info("removeUserContact()");            
       
        UserContactRemovingDto userContactRemovingDto = userContactService.removeUserContact(new UserContactRemovingDto("antoine.thomas@email", contactEmailAddress));
        
		if (userContactRemovingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("removing_message", userContactRemovingDto.getMessage());
		}

		return ("redirect:/contact");
    }
}

package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
    @GetMapping(value = "/contact")
    public String retrieveUserContactList(Model model) {
        logger.info("retrieveUserContactList()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
    	
        UserContactRetrievingDto userContactRetrievingDto = userContactService.retrieveUserContactList(new UserContactRetrievingDto(userEmailAddress));

        model.addAttribute("contact_list", userContactRetrievingDto.getUserContactList());
        
        return "/contact_list.html";
    }
	
    @PostMapping(value = "/contact/add")
    public String addUserContact(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "contact_email_address") String contactEmailAddress) {
    	
        logger.info("addUserContact()");     
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();   

       
        UserContactAddingDto userContactAddingDto = userContactService.addUserContact(new UserContactAddingDto(userEmailAddress, contactEmailAddress));
        
		if (userContactAddingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("adding_message", userContactAddingDto.getMessage());
		}
		
		return ("redirect:/contact");
    }
	
    @PostMapping(value = "/contact/remove")
    public String removeUserContact(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "contact_email_address") String contactEmailAddress) {
    	
        logger.info("removeUserContact()");  
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();          
       
        UserContactRemovingDto userContactRemovingDto = userContactService.removeUserContact(new UserContactRemovingDto(userEmailAddress, contactEmailAddress));
        
		if (userContactRemovingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("removing_message", userContactRemovingDto.getMessage());
		}

		return ("redirect:/contact");
    }
}

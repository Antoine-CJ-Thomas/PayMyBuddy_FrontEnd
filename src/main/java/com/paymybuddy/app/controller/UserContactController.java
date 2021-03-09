package com.paymybuddy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserContactController {

	
	public UserContactController() {
		
		
	}
	
    @GetMapping("/contact")
    public String login(Model model) {
        return "contact_list.html";
    }
}

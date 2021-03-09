package com.paymybuddy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {

	
	public UserAccountController() {
		
		
	}
	
    @GetMapping({"/", "/index"})
    public String login(Model model) {
        return "index.html";
    }
	
    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration.html";
    }
	
    @GetMapping("/home")
    public String home(Model model) {
        return "home.html";
    }
	
    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile.html";
    }
}

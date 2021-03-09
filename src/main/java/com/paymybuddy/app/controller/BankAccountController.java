package com.paymybuddy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankAccountController {

	
	public BankAccountController() {
		
		
	}
	
    @GetMapping("/bank")
    public String login(Model model) {
        return "bank_account_list.html";
    }
}

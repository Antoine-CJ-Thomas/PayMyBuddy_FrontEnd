package com.paymybuddy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternalTransactionController {

	
	public InternalTransactionController() {
		
		
	}
	
    @GetMapping("/contact-transfer")
    public String internalTransaction(Model model) {
        return "contact_transaction.html";
    }
}

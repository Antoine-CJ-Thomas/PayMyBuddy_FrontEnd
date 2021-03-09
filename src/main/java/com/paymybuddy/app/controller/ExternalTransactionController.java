package com.paymybuddy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExternalTransactionController {

	
	public ExternalTransactionController() {
		
		
	}
	
    @GetMapping("/bank-transfer")
    public String externalTransaction(Model model) {
        return "bank_account_transaction.html";
    }
}

package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.InternalTransactionExecutingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.service.InternalTransactionService;

@Controller
public class InternalTransactionController {

    private static final Logger logger = LogManager.getLogger("InternalTransactionController");

    @Autowired
	private InternalTransactionService internalTransactionService;
	
	
	public InternalTransactionController() {
        logger.info("InternalTransactionController()");
	}
	
    @GetMapping("/internal_transaction/list")
    public String retrieveInternalTransactionList(Model model) {
        logger.info("retrieveInternalTransactionList()");
    	
        InternalTransactionRetrievingDto internalTransactionRetrievingDto = internalTransactionService.retrieveInternalTransactionList(new InternalTransactionRetrievingDto("test"));

        model.addAttribute("internal_transaction_list", internalTransactionRetrievingDto.getInternalTransactionList());
        
        return "contact_transaction.html";
    }
	
    @PostMapping("/internal_transaction/execute")
    public String executeInternalTransaction(Model model, 
    		@RequestParam(value = "contact_email_address") String contactEmailAddress,
    		@RequestParam(value = "description") String description,
    		@RequestParam(value = "amount") float amount) {
    	
        logger.info("executeInternalTransaction()");
        	
        InternalTransactionExecutingDto internalTransactionExecutingDto = internalTransactionService.executeInternalTransaction(new InternalTransactionExecutingDto("test", contactEmailAddress, description, amount));
        InternalTransactionRetrievingDto InternalTransactionRetrievingDto = internalTransactionService.retrieveInternalTransactionList(new InternalTransactionRetrievingDto("test"));
        
		if (internalTransactionExecutingDto.isDataValidated()) {

	        model.addAttribute("internal_transaction_list", InternalTransactionRetrievingDto.getInternalTransactionList());
		}
		
		else {

	        model.addAttribute("internal_transaction_list", InternalTransactionRetrievingDto.getInternalTransactionList());
		    model.addAttribute("execution_message", internalTransactionExecutingDto.getMessage());
		}

        return "contact_transaction.html";
    }
}
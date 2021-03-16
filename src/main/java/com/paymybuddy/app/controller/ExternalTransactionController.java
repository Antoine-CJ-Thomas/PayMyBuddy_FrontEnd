package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.service.ExternalTransactionService;

@Controller
public class ExternalTransactionController {

    private static final Logger logger = LogManager.getLogger("ExternalTransactionController");

    @Autowired
	private ExternalTransactionService externalTransactionService;
	
	
	public ExternalTransactionController() {
        logger.info("ExternalTransactionController()");
	}
	
    @GetMapping("/External_transaction/list")
    public String retrieveExternalTransactionList(Model model) {
        logger.info("retrieveExternalTransactionList()");
    	
        ExternalTransactionRetrievingDto externalTransactionRetrievingDto = externalTransactionService.retrieveExternalTransactionList(new ExternalTransactionRetrievingDto("test"));

        model.addAttribute("External_transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
        
        return "bank_account_transaction.html";
    }
	
    @PostMapping("/External_transaction/execute")
    public String executeExternalTransaction(Model model, 
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode,
    		@RequestParam(value = "description") String description,
    		@RequestParam(value = "amount") float amount) {
    	
        logger.info("executeExternalTransaction()");
        	
        ExternalTransactionExecutingDto externalTransactionExecutingDto = externalTransactionService.executeExternalTransaction(new ExternalTransactionExecutingDto("test", accountNumber, swiftCode, description, amount));
        ExternalTransactionRetrievingDto externalTransactionRetrievingDto = externalTransactionService.retrieveExternalTransactionList(new ExternalTransactionRetrievingDto("test"));
        
		if (externalTransactionExecutingDto.isDataValidated()) {

	        model.addAttribute("external_transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
		}
		
		else {

	        model.addAttribute("external_transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
		    model.addAttribute("execution_message", externalTransactionExecutingDto.getMessage());
		}

        return "bank_account_transaction.html";
    }
}
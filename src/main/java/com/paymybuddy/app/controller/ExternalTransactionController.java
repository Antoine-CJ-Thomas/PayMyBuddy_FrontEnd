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

    @GetMapping(value = "/bank/transaction")
    public String retrieveExternalTransactionList(Model model) {
        logger.info("retrieveExternalTransactionList()");
    	
        ExternalTransactionRetrievingDto externalTransactionRetrievingDto = externalTransactionService.retrieveExternalTransactionList(new ExternalTransactionRetrievingDto("antoine.thomas@email"));

        model.addAttribute("External_transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
        
        return "bank_transaction.html";
    }
	
    @PostMapping(value = "/bank/transaction/execute")
    public String executeExternalTransaction(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode,
    		@RequestParam(value = "description") String description,
    		@RequestParam(value = "amount") float amount) {
    	
        logger.info("executeExternalTransaction()");
        	
        ExternalTransactionExecutingDto externalTransactionExecutingDto = externalTransactionService.executeExternalTransaction(new ExternalTransactionExecutingDto("antoine.thomas@email", accountNumber, swiftCode, description, amount));
        
		if (externalTransactionExecutingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("execution_message", externalTransactionExecutingDto.getMessage());
		}

		return ("redirect:/bank/transaction");
    }
}
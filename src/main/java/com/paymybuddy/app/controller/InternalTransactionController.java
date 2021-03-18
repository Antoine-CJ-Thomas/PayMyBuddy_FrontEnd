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
	
    @GetMapping(value = "/contact/transaction")
    public String retrieveInternalTransactionList(Model model) {
        logger.info("retrieveInternalTransactionList()");
    	
        InternalTransactionRetrievingDto internalTransactionRetrievingDto = internalTransactionService.retrieveInternalTransactionList(new InternalTransactionRetrievingDto("antoine.thomas@email"));

        model.addAttribute("internal_transaction_list", internalTransactionRetrievingDto.getInternalTransactionList());
        
        return "contact_transaction.html";
    }
	
    @PostMapping(value = "/contact/transaction/execute")
    public String executeInternalTransaction(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "contact_email_address") String contactEmailAddress,
    		@RequestParam(value = "description") String description,
    		@RequestParam(value = "amount") float amount) {
    	
        logger.info("executeInternalTransaction()");
        	
        InternalTransactionExecutingDto internalTransactionExecutingDto = internalTransactionService.executeInternalTransaction(new InternalTransactionExecutingDto("antoine.thomas@email", contactEmailAddress, description, amount));
        
		if (internalTransactionExecutingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("execution_message", internalTransactionExecutingDto.getMessage());
		}

		return ("redirect:/contact/transaction");
    }
}
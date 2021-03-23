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

import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.service.BankAccountService;
import com.paymybuddy.app.service.ExternalTransactionService;

@Controller
public class ExternalTransactionController {

    private static final Logger logger = LogManager.getLogger("ExternalTransactionController");

    @Autowired
	private ExternalTransactionService externalTransactionService;
    @Autowired
	private BankAccountService bankAccountService;
	
	
	public ExternalTransactionController() {
        logger.info("ExternalTransactionController()");
	}

    @GetMapping(value = "/bank/transaction")
    public String retrieveExternalTransactionList(Model model) {
        logger.info("retrieveExternalTransactionList()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
    	
        ExternalTransactionRetrievingDto externalTransactionRetrievingDto = externalTransactionService.retrieveExternalTransactionList(new ExternalTransactionRetrievingDto(userEmailAddress));

        model.addAttribute("transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
    	
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto(userEmailAddress));

        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
        
        return "bank_transaction.html";
    }
	
    @PostMapping(value = "/bank/transaction")
    public String executeExternalTransaction(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_name") String accountName,
    		@RequestParam(value = "description") String description,
    		@RequestParam(value = "amount") float amount) {
    	
        logger.info("executeExternalTransaction()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
        	
        ExternalTransactionExecutingDto externalTransactionExecutingDto = externalTransactionService.executeExternalTransaction(new ExternalTransactionExecutingDto(userEmailAddress, accountName, description, amount));
        
		if (externalTransactionExecutingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("execution_message", externalTransactionExecutingDto.getMessage());
		}

		return ("redirect:/bank/transaction");
    }
}
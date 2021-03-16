package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.service.BankAccountService;

@Controller
public class BankAccountController {

    private static final Logger logger = LogManager.getLogger("BankAccountController");

    @Autowired
	private BankAccountService bankAccountService;
	
	
	public BankAccountController() {
        logger.info("BankAccountController()");
	}
	
    @GetMapping("/bank_account/list")
    public String retrieveBankAccountList(Model model) {
        logger.info("retrieveBankAccountList()");
    	
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto("test"));

        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
        
        return "bank_account_list.html";
    }
	
    @PostMapping("/bank_account/add")
    public String addBankAccount(Model model, 
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode) {
    	
        logger.info("addBankAccount()");        
        	
        BankAccountAddingDto BankAccountAddingDto = bankAccountService.addBankAccount(new BankAccountAddingDto("test", accountNumber, swiftCode));
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto("test"));
        
		if (BankAccountAddingDto.isDataValidated()) {

	        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
		}
		
		else {

	        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
		    model.addAttribute("adding_message", BankAccountAddingDto.getMessage());
		}

        return "bank_account_list.html";
    }
	
    @PostMapping("/bank_account/delete")/////////////////////////////////POST NOT DELETE
    public String removeBankAccount(Model model,
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode) {
    	
        logger.info("removeBankAccount()");
   	
        BankAccountRemovingDto BankAccountRemovingDto = bankAccountService.removeBankAccount(new BankAccountRemovingDto("test", accountNumber, swiftCode));
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto("test"));
        
		if (BankAccountRemovingDto.isDataValidated()) {

	        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
		}
		
		else {

	        model.addAttribute("contact_list", BankAccountRetrievingDto.getBankAccountList());
		    model.addAttribute("removing_message", BankAccountRemovingDto.getMessage());
		}

        return "bank_account_list.html";
    }
}

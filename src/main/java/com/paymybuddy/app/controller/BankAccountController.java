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
	
    @GetMapping(value = "/bank")
    public String retrieveBankAccountList(Model model) {
        logger.info("retrieveBankAccountList()");
    	
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto("antoine.thomas@email"));

        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
        
        return "/bank_list.html";
    }
	
    @PostMapping(value = "/bank/add")
    public String addBankAccount(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_name") String accountName,
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode) {
    	
        logger.info("addBankAccount()");        
        	
        BankAccountAddingDto BankAccountAddingDto = bankAccountService.addBankAccount(new BankAccountAddingDto("antoine.thomas@email", accountName, accountNumber, swiftCode));
        
		if (BankAccountAddingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("adding_message", BankAccountAddingDto.getMessage());
		}

		return ("redirect:/bank");
    }
	
    @PostMapping(value = "/bank/remove")
    public String removeBankAccount(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_name") String accountName) {
    	
        logger.info("removeBankAccount()");
   	
        BankAccountRemovingDto BankAccountRemovingDto = bankAccountService.removeBankAccount(new BankAccountRemovingDto("antoine.thomas@email", accountName));
        
		if (BankAccountRemovingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("removing_message", BankAccountRemovingDto.getMessage());
		}

		return ("redirect:/bank");
    }
}

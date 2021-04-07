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

import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;
import com.paymybuddy.app.service.BankAccountService;

/**
 * This class allows to intercept bank account requests
 */
@Controller
public class BankAccountController {

    private static final Logger logger = LogManager.getLogger("BankAccountController");
    
    private String userEmailAddress;

    @Autowired
	private BankAccountService bankAccountService;
	
	
	public BankAccountController() {
        logger.info("BankAccountController()");
	}
	
    @GetMapping(value = "/bank")
    public String retrieveBankAccountList(Model model) {
        logger.info("retrieveBankAccountList()");
        
        if (SecurityContextHolder.getContext().getAuthentication() != null) {

        	userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
        }
    	
        BankAccountRetrievingDto BankAccountRetrievingDto = bankAccountService.retrieveBankAccountList(new BankAccountRetrievingDto(userEmailAddress));

        model.addAttribute("bank_account_list", BankAccountRetrievingDto.getBankAccountList());
        
        return "/bank_list.html";
    }
	
    @PostMapping(value = "/bank/add")
    public String addBankAccount(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_name") String accountName,
    		@RequestParam(value = "account_number") String accountNumber,
    		@RequestParam(value = "swift_code") String swiftCode) {
    	
        logger.info("addBankAccount()");        
        
        if (SecurityContextHolder.getContext().getAuthentication() != null) {

        	userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        	
        BankAccountAddingDto BankAccountAddingDto = bankAccountService.addBankAccount(new BankAccountAddingDto(userEmailAddress, accountName, accountNumber, swiftCode));
        
		if (BankAccountAddingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("adding_message", BankAccountAddingDto.getMessage());
		}

		return ("redirect:/bank");
    }
	
    @PostMapping(value = "/bank/remove")
    public String removeBankAccount(Model model, RedirectAttributes redirectAttributes, 
    		@RequestParam(value = "account_name") String accountName) {
    	
        logger.info("removeBankAccount()");
        
        if (SecurityContextHolder.getContext().getAuthentication() != null) {

        	userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
        }
   	
        BankAccountRemovingDto BankAccountRemovingDto = bankAccountService.removeBankAccount(new BankAccountRemovingDto(userEmailAddress, accountName));
        
		if (BankAccountRemovingDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("removing_message", BankAccountRemovingDto.getMessage());
		}

		return ("redirect:/bank");
    }
}

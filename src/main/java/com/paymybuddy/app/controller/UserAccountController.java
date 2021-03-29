package com.paymybuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;
import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountPayementDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;
import com.paymybuddy.app.service.ExternalTransactionService;
import com.paymybuddy.app.service.InternalTransactionService;
import com.paymybuddy.app.service.UserAccountService;

@Controller
public class UserAccountController {

    private static final Logger logger = LogManager.getLogger("UserAccountController");

    private String pageName;
    
    @Autowired
	private UserAccountService userAccountService;
    @Autowired
	private InternalTransactionService internalTransactionService;
    @Autowired
	private ExternalTransactionService externalTransactionService;
	
	
	public UserAccountController() {
        logger.info("UserAccountController()");
	}
	
    @GetMapping(value = "/registration")
    public String registrationWebPage(Model model) {
        logger.info("registrationWebPage()");               
        return "/registration.html";
    }

    @PostMapping(value = "/registration")
    public String createUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "email_address") String emailAddress, 
    		@RequestParam(value = "password") String password, 
    		@RequestParam(value = "first_name") String firstName, 
    		@RequestParam(value = "last_name") String lastName) {
    	
        logger.info("createUserAccount()");      
        
        String cryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        	
        UserAccountCreatingDto userAccountCreatingDto = userAccountService.createUserAccount(new UserAccountCreatingDto(emailAddress, cryptedPassword, firstName, lastName));
                
		if (userAccountCreatingDto.isDataValidated()) {
	        
        	pageName = "/login";
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("registration_message", userAccountCreatingDto.getMessage());
        	pageName = "/registration";
		}
		
		return ("redirect:" + pageName);
    }
	
    @GetMapping(value = {"/", "/login"})
    public String loginWebPage(Model model, String error) {
        logger.info("loginWebPage()");
                
        if(error != null) {
        	
        	model.addAttribute("login_message", "Invalid email or password");
        }
        
        return "/login.html";
    }
	
    @GetMapping(value = "/home")
    public String homeWebPage(Model model) {
        logger.info("homeWebPage()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
                
    	UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));
        model.addAttribute("balance", userAccountRetrievingDto.getUserAccount().getBalanceAmount());
        
        InternalTransactionRetrievingDto internalTransactionRetrievingDto = internalTransactionService.retrieveInternalTransactionList(new InternalTransactionRetrievingDto(userEmailAddress));
        model.addAttribute("contact_transaction_list", internalTransactionRetrievingDto.getInternalTransactionList());
    	
        ExternalTransactionRetrievingDto externalTransactionRetrievingDto = externalTransactionService.retrieveExternalTransactionList(new ExternalTransactionRetrievingDto(userEmailAddress));
        model.addAttribute("bank_account_transaction_list", externalTransactionRetrievingDto.getExternalTransactionList());
        
        return "/home.html";
    }
	
    @GetMapping(value = "/balance")
    public String balanceWebPage(Model model) {
        logger.info("balanceWebPage()");          
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
                
    	UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));
        model.addAttribute("balance", userAccountRetrievingDto.getUserAccount().getBalanceAmount());
        
        return "/balance.html";
    }

    @PostMapping(value = "/balance")
    public String addMoneyToBalance(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "card_number") String cardNumber, 
    		@RequestParam(value = "card_expiration") String cardExpiration, 
    		@RequestParam(value = "card_cryptogram") String cardCryptogram, 
    		@RequestParam(value = "payement_amount") float payementAmount) {
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	UserAccountPayementDto userAccountPayementDto = userAccountService.addMoneyToBalance(new UserAccountPayementDto(userEmailAddress, cardNumber, cardExpiration, cardCryptogram, payementAmount));
        
		if (userAccountPayementDto.isDataValidated() == false) {

			redirectAttributes.addFlashAttribute("payement_message", userAccountPayementDto.getMessage());
		}
    	
		return ("redirect:/balance");
    }
	
    @GetMapping(value = "/profile")
    public String profileWebPage(Model model) {
        logger.info("profileWebPage()");
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
    	
        UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));

        model.addAttribute("email_address", userAccountRetrievingDto.getUserAccount().getEmailAddress());
        model.addAttribute("first_name", userAccountRetrievingDto.getUserAccount().getFirstName());
        model.addAttribute("last_name", userAccountRetrievingDto.getUserAccount().getLastName());

        return "/profile.html";
    }

	@PostMapping(value = "/account/edit")
	public String editUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "first_name") String first_name,
    		@RequestParam(value = "last_name") String last_name) {
		
        logger.info("editUserAccount()");        
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
        
        String cryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
   	
        UserAccountEditingDto userAccountEditingDto = userAccountService.editUserAccount(new UserAccountEditingDto(userEmailAddress, cryptedPassword, first_name, last_name));
	    
		if (userAccountEditingDto.isDataValidated()) {
			
			pageName = "/profile";
		}
		
		else {
			
			redirectAttributes.addFlashAttribute("edition_message", userAccountEditingDto.getMessage());
			pageName = "/profile";
		}

		return ("redirect:" + pageName);
	}
	
    @PostMapping(value = "/account/delete")
    public String deleteUserAccount(Model model, RedirectAttributes redirectAttributes,
    		@RequestParam(value = "password") String password) {
    	
        logger.info("deleteUserAccount()");        
        
        String userEmailAddress = SecurityContextHolder.getContext().getAuthentication().getName();
   	
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();   
        
        UserAccountRetrievingDto userAccountRetrievingDto = userAccountService.retrieveUserAccount(new UserAccountRetrievingDto(userEmailAddress));
        
        if(passwordEncoder.matches(password,userAccountRetrievingDto.getUserAccount().getPassword())) {
            
    	    UserAccountDeletingDto userAccountDeletingDto = userAccountService.deleteUserAccount(new UserAccountDeletingDto(userEmailAddress));
    	    
    		if (userAccountDeletingDto.isDataValidated()) {
    	
    			pageName = "/login";
    		}
    		
    		else {

    			redirectAttributes.addFlashAttribute("deletion_message", userAccountDeletingDto.getMessage());
    			pageName = "/profile";
    		}
        }
		
		else {

			redirectAttributes.addFlashAttribute("deletion_message", "Invalid password");
			pageName = "/profile";
		}
		
		return ("redirect:" + pageName);
    }
}

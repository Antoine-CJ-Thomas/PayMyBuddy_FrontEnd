package com.paymybuddy.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.BankAccountAddingDto;
import com.paymybuddy.app.dto.BankAccountRemovingDto;
import com.paymybuddy.app.dto.BankAccountRetrievingDto;

@FeignClient(name = "app-backend", url = "localhost:8080/bank")
public interface BankAccountProxy {

	@PostMapping(value = "/account")
	public BankAccountAddingDto addBankAccount(@RequestBody BankAccountAddingDto bankAccountCreatingDto);

	@DeleteMapping(value = "/account")
	public BankAccountRemovingDto removeBankAccount(@RequestBody BankAccountRemovingDto bankAccountDeletingDto);

	@GetMapping(value = "/account")
	public BankAccountRetrievingDto retrieveBankAccountList(@RequestParam BankAccountRetrievingDto bankAccountRetrievingDto);
}
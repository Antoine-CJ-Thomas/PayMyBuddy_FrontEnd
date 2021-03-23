package com.paymybuddy.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.UserAccountCreatingDto;
import com.paymybuddy.app.dto.UserAccountDeletingDto;
import com.paymybuddy.app.dto.UserAccountEditingDto;
import com.paymybuddy.app.dto.UserAccountRetrievingDto;

@FeignClient(name = "app-backend", url = "localhost:8080/user")
public interface UserAccountProxy {

	@PostMapping(value = "/account")
	public UserAccountCreatingDto createUserAccount(@RequestBody UserAccountCreatingDto userAccountCreatingDto);

	@DeleteMapping(value = "/account")
	public UserAccountDeletingDto deleteUserAccount(@RequestBody UserAccountDeletingDto userAccountDeletingDto);

	@PutMapping(value = "/account")
	public UserAccountEditingDto editUserAccount(@RequestBody UserAccountEditingDto userAccountEditingDto);

	@GetMapping(value = "/account")
	public UserAccountRetrievingDto retrieveUserAccount(@RequestParam String emailAddress);
}
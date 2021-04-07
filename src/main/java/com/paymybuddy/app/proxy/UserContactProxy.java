package com.paymybuddy.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.UserContactAddingDto;
import com.paymybuddy.app.dto.UserContactRemovingDto;
import com.paymybuddy.app.dto.UserContactRetrievingDto;

@FeignClient(name = "app-backend", url = "localhost:8080/user")
public interface UserContactProxy {

	@PostMapping(value = "/contact")
	public UserContactAddingDto addUserContact(@RequestBody UserContactAddingDto userContactCreatingDto);

	@DeleteMapping(value = "/contact")
	public UserContactRemovingDto removeUserContact(@RequestBody UserContactRemovingDto userContactDeletingDto);

	@GetMapping(value = "/contact")
	public UserContactRetrievingDto retrieveUserContactList(@RequestParam String emailAddress);
}
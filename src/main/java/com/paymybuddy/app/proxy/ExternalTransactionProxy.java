package com.paymybuddy.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.ExternalTransactionExecutingDto;
import com.paymybuddy.app.dto.ExternalTransactionRetrievingDto;

@FeignClient(name = "app-backend", url = "localhost:8080/transaction")
public interface ExternalTransactionProxy {

	@PostMapping(value = "/external")
	public ExternalTransactionExecutingDto executeExternalTransaction(@RequestBody ExternalTransactionExecutingDto externalTransactionCreatingDto);

	@GetMapping(value = "/external")
	public ExternalTransactionRetrievingDto retrieveExternalTransactionList(@RequestParam ExternalTransactionRetrievingDto externalTransactionRetrievingDto);
}
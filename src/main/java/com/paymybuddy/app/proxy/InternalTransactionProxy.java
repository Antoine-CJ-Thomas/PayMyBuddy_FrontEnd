package com.paymybuddy.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.dto.InternalTransactionExecutingDto;
import com.paymybuddy.app.dto.InternalTransactionRetrievingDto;

/**
 * This class allows to send queries to a InternalTransactionController on an another server
 */
@FeignClient(name = "app-backend", url = "localhost:8080/transaction")
public interface InternalTransactionProxy {

	@PostMapping(value = "/internal")
	public InternalTransactionExecutingDto executeInternalTransaction(@RequestBody InternalTransactionExecutingDto internalTransactionExecutingDto);

	@GetMapping(value = "/internal")
	public InternalTransactionRetrievingDto retrieveInternalTransactionList(@RequestParam String emailAddress);
}
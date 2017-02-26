package com.worldline.fpl.recruitment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.json.ErrorResponse;
import com.worldline.fpl.recruitment.json.TransactionResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Transaction controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

	/**
	 * Get transaction list by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return the transaction list
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get transaction list related to an account", response = TransactionResponse.class, responseContainer = "List")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 204, message = "No transactions", response = ErrorResponse.class) })
	ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Pageable information") @PageableDefault Pageable p);
	
	/**
	 * Delete transaction By ID
	 * 
	 * @param transaction
	 *            the entity transaction
	 */
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	Boolean deleteTransactionsById(@PathVariable("transactionId") String transactionId);
	
	/**
	 * Delete All transaction By AccountID
	 */
	@RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
	public Boolean deleteAllTransactionsByAccount(@PathVariable("accountId") String accountId);
}

package com.worldline.fpl.recruitment.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.TransactionController;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link TransactionController}
 * 
 * @author A525125
 *
 */
@Slf4j
@RestController
public class TransactionControllerImpl implements TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionControllerImpl(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@Override
	public ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@PathVariable("accountId") String accountId,
			@PageableDefault Pageable p) {
		Page<TransactionResponse> page = transactionService
				.getTransactionsByAccount(accountId, p);
		if (null == page || page.getTotalElements() == 0) {
			log.debug("Cannot find transaction for account {}", accountId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok().body(page);
	}

	@Override
	public Boolean deleteTransactionsById(@PathVariable("transactionId") String transactionId, 
			@PathVariable("accountId") String accountId) {
		Boolean result = transactionService.deleteTransactionsById(transactionId, accountId);
		if(!result) {
			log.debug("Cannot find transaction for id ", transactionId);
		}
		return result;
	}
	
	@Override
	public Boolean deleteAllTransactionsByAccount(@PathVariable("accountId") String accountId) {
		Boolean result = transactionService.deleteAllTransactionsByAccount(accountId);
		if(!result) {
			log.debug("Cannot find account ", accountId);
		}
		return result;
	}

	@Override
	public Transaction addTransaction(@RequestBody Transaction transaction) {
		return transactionService.addTransaction(transaction);
	}

	@Override
	public Transaction UpdateTransaction(@RequestBody Transaction transaction) {
		return transactionService.UpdateTransaction(transaction);
	}
}

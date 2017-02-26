package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);
	
	/**
	 * Get transactions by id of transaction
	 * 
	 * @param transactionId
	 *            the id of transaction
	 */
	Boolean deleteTransactionsById(String transactionId);
	
	/**
	 * Check if a transaction exists in the liste
	 * 
	 * @param transactionId
	 *            the id of transaction
	 * @return true if the transaction exists
	 */
	boolean exists(String transactionId);
	
	/**
	 * delete all transaction
	 */
	boolean deleteAllTransactionsByAccount(String accountId);
}

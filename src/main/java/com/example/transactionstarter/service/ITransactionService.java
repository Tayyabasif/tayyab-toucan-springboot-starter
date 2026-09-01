package com.example.transactionstarter.service;

import java.util.List;

import com.example.transactionstarter.dto.TransactionRequest;
import com.example.transactionstarter.dto.TransactionResponse;
import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionStatus;

public interface ITransactionService {

	List<TransactionResponse> allTransactionList();
	
	List<TransactionResponse> allTransactionList(String customerId);
	
	Transaction findTransaction(String id);
	
	TransactionResponse addTransaction(TransactionRequest txn);
	
	TransactionResponse updateTransactionStatus(String id, TransactionStatus newStatus);
}

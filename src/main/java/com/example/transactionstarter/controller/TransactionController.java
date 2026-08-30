package com.example.transactionstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionStatus;
import com.example.transactionstarter.repository.TransactionRepo;
import com.example.transactionstarter.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService service;

	@GetMapping("/transactions")
	public List<Transaction> getTransaction(){
		
		return service.allTransactionList();
	}
	
	@GetMapping("/transactions/{customerId}")
	public List<Transaction> getCustomerTransactions(@PathVariable String customerId){
		
		return service.allTransactionList(customerId);
	}
	
	@PostMapping("/transaction")
	public void createTransaction(@RequestBody Transaction transaction) {
		
		service.addTransaction(transaction);
	}
	
	@PutMapping("/transaction/{transactionId}/{transactionStatus}")
	public Transaction updateTransaction(@PathVariable String transactionId, @PathVariable TransactionStatus transactionStatus){
		
		return service.updateTransactionStatus(transactionId, transactionStatus);
	}
	
}

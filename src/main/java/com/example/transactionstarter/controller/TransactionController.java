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

@RestController
public class TransactionController {
	
	@Autowired
	TransactionRepo repo;

	@GetMapping("/transactions")
	public List<Transaction> getTransaction(){
		
		return repo.findAll();
	}
	
	@GetMapping("/transactions/{customerId}")
	public List<Transaction> getCustomerTransactions(@PathVariable String customerId){
		
		return repo.findByCustomerId(customerId);
	}
	
	@PostMapping("/transaction")
	public void createTransaction(@RequestBody Transaction transaction) {
		
		if(repo.existsById(transaction.getTransactionId()))
			throw new IllegalArgumentException("duplicate aadmi kahi ka");
		repo.save(transaction);
	}
	
	@PutMapping("/transaction/{transactionId}/{transactionStatus}")
	public Transaction updateTransaction(@PathVariable String transactionId, @PathVariable TransactionStatus transactionStatus){
		
		Transaction transaction = repo.findById(transactionId).orElse(null);
		transaction.setTransactionStatus(transactionStatus);
		return repo.save(transaction);
	}
	
	
}

package com.example.transactionstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionstarter.dto.ApiResponse;
import com.example.transactionstarter.dto.TransactionRequest;
import com.example.transactionstarter.dto.TransactionResponse;
import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionStatus;
import com.example.transactionstarter.repository.TransactionRepo;
import com.example.transactionstarter.service.ITransactionService;
import com.example.transactionstarter.service.TransactionServiceImpl;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
	
	
	private final ITransactionService service;

	
	public TransactionController(ITransactionService service) {
		this.service = service;	
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransaction(){
		ApiResponse<List<TransactionResponse>> response = new ApiResponse<>(
				"/api/v1/transactions", 
				service.allTransactionList());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<ApiResponse<List<TransactionResponse>>> getCustomerTransactions(@PathVariable String customerId){
		
		ApiResponse<List<TransactionResponse>> response = new ApiResponse<>(
				"/api/v1/transactions/"+customerId, 
				service.allTransactionList(customerId));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
		
	@PostMapping
	public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest transaction) {
		
		ApiResponse<TransactionResponse> response = new ApiResponse<>(
				"/api/v1/transactions", 
				service.addTransaction(transaction));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PatchMapping("/{transactionId}/{transactionStatus}")
	public ResponseEntity<ApiResponse<TransactionResponse>> updateTransaction(@PathVariable String transactionId, @PathVariable TransactionStatus transactionStatus){
		
		ApiResponse<TransactionResponse> response = new ApiResponse<>(
				"/api/v1/transactions/"+transactionId+"/"+transactionStatus, 
				service.updateTransactionStatus(transactionId, transactionStatus));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}

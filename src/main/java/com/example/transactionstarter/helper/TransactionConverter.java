package com.example.transactionstarter.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.transactionstarter.dto.TransactionRequest;
import com.example.transactionstarter.dto.TransactionResponse;
import com.example.transactionstarter.entity.Transaction;

@Component
public class TransactionConverter {
	
	public Transaction toEntity(TransactionRequest dto) {
		
		Transaction transaction = new Transaction();
		
		transaction.setTransactionId(dto.getTransactionId());
		transaction.setCustomerId(dto.getCustomerId());
		transaction.setAmount(dto.getAmount());
		transaction.setCurrency(dto.getCurrency());
		transaction.setTransactionType(dto.getTransactionType());
		
		return transaction;
	}
	
	public TransactionResponse toResponseDto(Transaction transaction) {
		
		TransactionResponse dto = new TransactionResponse();
		
		dto.setTransactionId(transaction.getTransactionId());
		dto.setCustomerId(transaction.getCustomerId());
		dto.setAmount(transaction.getAmount());
		dto.setCurrency(transaction.getCurrency());
		dto.setTransactionType(transaction.getTransactionType());
		dto.setTransactionStatus(transaction.getTransactionStatus());
		
		return dto;	      
	}
	
	 public List<TransactionResponse> toResponseDtoList(List<Transaction> transactions) {

	        return transactions.stream()
	                .map(this::toResponseDto)
	                .toList();
	    }
}

package com.example.transactionstarter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transactionstarter.dto.TransactionRequest;
import com.example.transactionstarter.entity.Currency;
import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionType;
import com.example.transactionstarter.exception.DuplicateTransactionException;
import com.example.transactionstarter.exception.TransactionNotFoundException;
import com.example.transactionstarter.repository.TransactionRepo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTests {
	
	@Autowired
	TransactionRepo repo;
	
	@Autowired
	ITransactionService service;

	@Test
	public void validateCreateTransaction() {
		
		TransactionRequest txn = new TransactionRequest("txn01", "cst01", 200.00, Currency.USD, TransactionType.NEFT);
		service.addTransaction(txn);
		assertNotNull(repo.findById(txn.getTransactionId()).orElse(null));
	}
		
	@Test
	public void validateTransactionThrowsDuplicateTransaction() {

		TransactionRequest txn1 = new TransactionRequest("txn02", "cst02", 200.00, Currency.USD, TransactionType.NEFT);
		service.addTransaction(txn1);
		TransactionRequest txn2 = new TransactionRequest("txn02", "cst02", 200.00, Currency.USD, TransactionType.NEFT);
		assertThrows(DuplicateTransactionException.class, () -> service.addTransaction(txn2));
	}
	
	@Test
	public void validateTransactionThrowsTransactionNotFound() {
		
		String customerId = "missing";
		assertThrows(TransactionNotFoundException.class, () -> service.allTransactionList(customerId));
	}
	
	@Test
	public void rejectsInvalidAmountException() {
		
		TransactionRequest txn = new TransactionRequest("txn02", "cst01", 00, Currency.USD, TransactionType.NEFT);
		assertThrows(IllegalArgumentException.class, () -> service.addTransaction(txn));
		assertNull(repo.findById(txn.getTransactionId()).orElse(null));
	}
	
}

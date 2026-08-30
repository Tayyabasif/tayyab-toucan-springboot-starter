package com.example.transactionstarter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	TransactionService service;

	@Test
	public void validateCreateTransaction() {
		
		Transaction txn = new Transaction("txn01", "cst01", 200.00, Currency.Dollar, TransactionType.NEFT);
		service.addTransaction(txn);
		assertNotNull(repo.findById(txn.getTransactionId()).orElse(null));
	}
		
	@Test
	public void validateTransactionThrowsDuplicateTransaction() {

		Transaction txn1 = new Transaction("txn02", "cst02", 200.00, Currency.Dollar, TransactionType.NEFT);
		service.addTransaction(txn1);
		Transaction txn2 = new Transaction("txn02", "cst02", 200.00, Currency.Dollar, TransactionType.NEFT);
		assertThrows(DuplicateTransactionException.class, () -> service.addTransaction(txn2));
	}
	
	@Test
	public void validateTransactionThrowsTransactionNotFound() {
		
		String customerId = "missing";
		assertThrows(TransactionNotFoundException.class, () -> service.allTransactionList(customerId));
	}
	
	@Test
	public void rejectsInvalidAmountException() {
		
		Transaction txn = new Transaction("txn02", "cst01", 00, Currency.Dollar, TransactionType.NEFT);
		assertThrows(IllegalArgumentException.class, () -> service.addTransaction(txn));
		assertNull(repo.findById(txn.getTransactionId()).orElse(null));
	}
	
}

package com.example.transactionstarter.exception;

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(String customerId) {
		super("Customer not found with : " + customerId);
	}
}

package com.example.transactionstarter.exception;

public class DuplicateTransactionException extends RuntimeException{
	
	public DuplicateTransactionException() {
		super("Transaction ID already exists");
	}
}

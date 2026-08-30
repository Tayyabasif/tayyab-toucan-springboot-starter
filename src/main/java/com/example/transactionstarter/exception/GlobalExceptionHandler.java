package com.example.transactionstarter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class, InvalidStatusTransitionException.class})
	public ResponseEntity<String> handleBadRequest(RuntimeException ex){
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateTransactionException.class)
	public ResponseEntity<String> handleDuplicateTransactionException(DuplicateTransactionException ex){
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException ex){
		
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}

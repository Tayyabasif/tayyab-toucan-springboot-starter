package com.example.transactionstarter.dto;

import com.example.transactionstarter.entity.Currency;
import com.example.transactionstarter.entity.TransactionStatus;
import com.example.transactionstarter.entity.TransactionType;


public class TransactionRequest {

	private String transactionId;
	private String customerId;
	private double amount;
	private Currency currency;
	private TransactionType transactionType;
	private TransactionStatus transactionStatus;
	
	public TransactionRequest() {

	}
	
	public TransactionRequest(String transactionId, String customerId, double amount, Currency currency,
			TransactionType transactionType) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.amount = amount;
		this.currency = currency;
		this.transactionType = transactionType;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	
	
}

package com.example.transactionstarter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Transaction {

	@Id
	private String transactionId;
	private String customerId;
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	
	public Transaction() {
		
	}
	
	public Transaction(String transactionId, String customerId, double amount, Currency currency,
			TransactionType transactionType, TransactionStatus transactionStatus) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.amount = amount;
		this.currency = currency;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
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

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", amount=" + amount
				+ ", currency=" + currency + ", transactionType=" + transactionType + ", transactionStatus="
				+ transactionStatus + "]";
	}

	
}

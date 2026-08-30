package com.example.transactionstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transactionstarter.entity.Currency;
import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionStatus;
import com.example.transactionstarter.entity.TransactionType;
import com.example.transactionstarter.exception.DuplicateTransactionException;
import com.example.transactionstarter.exception.InvalidStatusTransitionException;
import com.example.transactionstarter.exception.TransactionNotFoundException;
import com.example.transactionstarter.repository.TransactionRepo;

@Service
public class TransactionService {
	
@Autowired
TransactionRepo repo;

public List<Transaction> allTransactionList(){
		
		return repo.findAll();
	}

public List<Transaction> allTransactionList(String customerId){
	
	List<Transaction> ctl =repo.findByCustomerId(customerId);
	
	if(ctl.isEmpty())
		throw new TransactionNotFoundException(customerId);
	
	return ctl;
}

public Transaction findTransaction(String id){
	
	Transaction txn =repo.findById(id).orElse(null);
	
	if(txn == null)
		throw new TransactionNotFoundException(id);
	
	return txn;
}

public void addTransaction(Transaction txn){
	
	String id = txn.getTransactionId();
	String customerId = txn.getCustomerId();
	
	if(id.isEmpty() || customerId.isEmpty()) throw new IllegalArgumentException("ID's cannot be blank");
	if(txn.getAmount()<=0) throw new IllegalArgumentException("Amount  must be greater than zero");
	if(txn.getAmount()>1000000) throw new IllegalArgumentException("Amount must not exceed 1000000");
	if(repo.existsById(txn.getTransactionId())) throw new DuplicateTransactionException();
	repo.save(new Transaction(id, customerId, txn.getAmount(), txn.getCurrency(),
			txn.getTransactionType(), TransactionStatus.Pending));
}

public Transaction updateTransactionStatus(String id, TransactionStatus newStatus) {
	Transaction txn = findTransaction(id);
	TransactionStatus txnStatus = txn.getTransactionStatus();
	if(newStatus == txnStatus) return txn;
		
	if(txnStatus == TransactionStatus.Completed || txnStatus == TransactionStatus.Failed) {
		throw new InvalidStatusTransitionException("Status Transition not allowed from " +txnStatus+ " to " +newStatus);
	}
	
	txn.setTransactionStatus(newStatus);
	return repo.save(txn);
}

}

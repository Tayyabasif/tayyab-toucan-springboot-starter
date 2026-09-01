package com.example.transactionstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.transactionstarter.dto.TransactionRequest;
import com.example.transactionstarter.dto.TransactionResponse;
import com.example.transactionstarter.entity.Currency;
import com.example.transactionstarter.entity.Transaction;
import com.example.transactionstarter.entity.TransactionStatus;
import com.example.transactionstarter.entity.TransactionType;
import com.example.transactionstarter.exception.DuplicateTransactionException;
import com.example.transactionstarter.exception.InvalidStatusTransitionException;
import com.example.transactionstarter.exception.TransactionNotFoundException;
import com.example.transactionstarter.helper.TransactionConverter;
import com.example.transactionstarter.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements ITransactionService {
	

private final TransactionRepo repo;
private final TransactionConverter transactionConverter;


public TransactionServiceImpl(TransactionRepo repo, TransactionConverter transactionConverter) {
	this.repo = repo;
	this.transactionConverter = transactionConverter;
}

@Override
public List<TransactionResponse> allTransactionList(){
		
		return transactionConverter.toResponseDtoList(repo.findAll());
	}

@Override
public List<TransactionResponse> allTransactionList(String customerId){
	
	List<Transaction> ctl =repo.findByCustomerId(customerId);
	
	if(ctl.isEmpty())
		throw new TransactionNotFoundException("Transaction not found with customer id: " +customerId);
	
	return transactionConverter.toResponseDtoList(ctl);
}

@Override
public Transaction findTransaction(String id){
	
	Transaction txn =repo.findById(id).orElse(null);
	
	if(txn == null)
		throw new TransactionNotFoundException("Transaction not found with transaction id: " +id);
	
	return txn;
}

@Override
public TransactionResponse addTransaction(TransactionRequest transactionRequest){
	
	String id = transactionRequest.getTransactionId();
	String customerId = transactionRequest.getCustomerId();
	
	if(id.isEmpty() || customerId.isEmpty()) throw new IllegalArgumentException("ID's cannot be blank");
	if(transactionRequest.getAmount()<=0) throw new IllegalArgumentException("Amount  must be greater than zero");
	if(transactionRequest.getAmount()>1000000) throw new IllegalArgumentException("Amount must not exceed 1000000");
	if(repo.existsById(transactionRequest.getTransactionId())) throw new DuplicateTransactionException();
	
	Transaction txn = transactionConverter.toEntity(transactionRequest);
	txn.setTransactionStatus(TransactionStatus.Pending);
	
	return transactionConverter.toResponseDto(repo.save(txn));
}

@Override
public TransactionResponse updateTransactionStatus(String id, TransactionStatus newStatus) {
	Transaction txn = findTransaction(id);
	TransactionStatus txnStatus = txn.getTransactionStatus();
	if(newStatus == txnStatus) return transactionConverter.toResponseDto(txn);
		
	if(txnStatus == TransactionStatus.Completed || txnStatus == TransactionStatus.Failed) {
		throw new InvalidStatusTransitionException("Status Transition not allowed from " +txnStatus+ " to " +newStatus);
	}
	
	txn.setTransactionStatus(newStatus);
	return transactionConverter.toResponseDto(repo.save(txn));
}

}

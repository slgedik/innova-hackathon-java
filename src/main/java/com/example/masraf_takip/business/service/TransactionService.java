package com.example.masraf_takip.business.service;

import java.util.List;

import com.example.masraf_takip.entities.Transaction;

public interface TransactionService {
	//Transaction saveTransaction(Long userId, Transaction transaction);
	Transaction saveTransaction(Transaction transaction);
    //double getTotalExpensesByUserId(Long userId);
    double getTotalExpensesByUserId();
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    void deleteTransactionById(Long id);
    Transaction updateTransaction(Long id, Transaction transaction);
}
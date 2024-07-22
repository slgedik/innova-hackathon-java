package com.example.masraf_takip.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.masraf_takip.business.service.TransactionService;
import com.example.masraf_takip.entities.Transaction;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    
    @PostMapping("/add")
    public ResponseEntity<Transaction> createTransaction( @RequestBody Transaction transaction) {
        try {
        	Transaction newTransaction = transactionService.saveTransaction(transaction);
        	return ResponseEntity.ok(newTransaction);
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    	
    }
    /*@GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }*/


    @GetMapping("/user/total")
    public ResponseEntity<Double> getTotalExpenses() {
    	double totalExpenses = transactionService.getTotalExpensesByUserId();
        return ResponseEntity.ok(totalExpenses);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
    	List<Transaction> transactions = transactionService.getAllTransactions(); 
    	return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
    	Transaction transaction = transactionService.getTransactionById(id);
    	return ResponseEntity.ok(transaction);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
        	Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        	return ResponseEntity.ok(updatedTransaction);
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    	
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}
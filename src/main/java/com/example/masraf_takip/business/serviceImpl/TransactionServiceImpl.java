package com.example.masraf_takip.business.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.masraf_takip.business.service.TransactionService;
import com.example.masraf_takip.dataAccess.TransactionRepository;
import com.example.masraf_takip.dataAccess.UserRepository;
import com.example.masraf_takip.entities.Transaction;
import com.example.masraf_takip.entities.User;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthService authService;

    
    @Override
    public Transaction saveTransaction(Transaction transaction) {
    	Long currentUserId = authService.getCurrentUserId();
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new RuntimeException("User not found"));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    
    @Override
    public double getTotalExpensesByUserId() {
        Long currentUserId = authService.getCurrentUserId();

        List<Transaction> transactions = transactionRepository.findByUserId(currentUserId);
        double totalExpenses = 0.0;
        
        for (Transaction transaction : transactions) {
            totalExpenses += transaction.getAmount();
        }
        
        return totalExpenses;
    }
    

    @Override
    public List<Transaction> getAllTransactions() {
        Long currentUserId = authService.getCurrentUserId();
        return transactionRepository.findByUserId(currentUserId);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        Long currentUserId = authService.getCurrentUserId();
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null || !transaction.getUser().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You can only access your own transactions");
        }
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Long currentUserId = authService.getCurrentUserId();
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null || !existingTransaction.getUser().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You can only update your own transactions");
        }
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());
        existingTransaction.setDescription(transaction.getDescription());
        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransactionById(Long id) {
        Long currentUserId = authService.getCurrentUserId();
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null || !transaction.getUser().getId().equals(currentUserId)) {
            throw new AccessDeniedException("You can only delete your own transactions");
        }
        transactionRepository.deleteById(id);
    }

	
}

package com.example.masraf_takip.business.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.masraf_takip.dataAccess.TransactionRepository;
import com.example.masraf_takip.dataAccess.UserRepository;
import com.example.masraf_takip.entities.Transaction;
import com.example.masraf_takip.entities.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public void aggregateExpenses(String period) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            LocalDate now = LocalDate.now();
            LocalDate startDate = getStartDate(now, period);

            List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(user.getId(), startDate, now);

            double total = 0.0;
            for (Transaction transaction : transactions) {
                total += transaction.getAmount();
            }

            // toplam masrafı yazdırma
            System.out.println("User ID: " + user.getId() + ", Period: " + period + ", Total Expense: " + total + " TL");
        }
    }

    private LocalDate getStartDate(LocalDate now, String period) {
        switch (period) {
            case "DAILY":
                return now.minus(1, ChronoUnit.DAYS);
            case "WEEKLY":
                return now.minus(1, ChronoUnit.WEEKS);
            case "MONTHLY":
                return now.minus(1, ChronoUnit.MONTHS);
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }
    }
}
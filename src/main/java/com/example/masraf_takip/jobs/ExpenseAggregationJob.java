package com.example.masraf_takip.jobs;

import org.springframework.stereotype.Component;

import com.example.masraf_takip.business.serviceImpl.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
@Component
public class ExpenseAggregationJob {

    @Autowired
    private ExpenseService expenseService;

    
    @Scheduled(fixedRate= 10000)
    //@Scheduled(cron = "0 0 0 * * ?") // Günlük 
    public void aggregateDailyExpenses() {
    	System.out.println("Daily aggregation job started");
        expenseService.aggregateExpenses("DAILY");
    }
    @Scheduled(fixedRate= 10000)
    //@Scheduled(cron = "0 0 0 * * MON") // Haftalık 
    public void aggregateWeeklyExpenses() {
    	System.out.println("Weekly aggregation job started");
        expenseService.aggregateExpenses("WEEKLY");
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Aylık
    public void aggregateMonthlyExpenses() {
    	System.out.println("Monthly aggregation job started");
        expenseService.aggregateExpenses("MONTHLY");
    }
}
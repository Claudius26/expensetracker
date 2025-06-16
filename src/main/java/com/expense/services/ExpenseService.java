package com.expense.services;

import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.response.ExpenseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest expenseRequest);
    List<ExpenseResponse> viewAllExpenses(String userEmail);
    double calculateTotalExpense(String userEmail);
}

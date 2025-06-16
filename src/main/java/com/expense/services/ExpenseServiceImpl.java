package com.expense.services;

import com.expense.data.model.Expense;
import com.expense.data.repository.Expenses;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.expense.utils.Mapper.map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private Expenses expenses;

    @Override
    public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {
        return map(expenses.save(map(expenseRequest)));
    }
    @Override
    public List<ExpenseResponse> viewAllExpenses() {
        return List.of();
    }

    @Override
    public double calculateTotalExpense() {
        return 0;
    }
}

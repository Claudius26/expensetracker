package com.expense.utils;

import com.expense.data.model.Expense;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.response.ExpenseResponse;

public class Mapper {


    public static Expense map(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setDate(expenseRequest.getDate());
        return expense;
    }

    public static ExpenseResponse map(Expense expense){
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setDescription(expense.getDescription());
        expenseResponse.setAmount(expense.getAmount());
        expenseResponse.setDate(expense.getDate());
        return expenseResponse;
    }
}

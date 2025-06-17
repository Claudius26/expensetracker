package com.expense.exceptions;

public class NoExpenseAddedException extends ExpenseException {
    public NoExpenseAddedException(String message) {
        super(message);
    }
}

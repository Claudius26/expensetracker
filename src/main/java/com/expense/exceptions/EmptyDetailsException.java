package com.expense.exceptions;

public class EmptyDetailsException extends ExpenseException {
    public EmptyDetailsException(String message) {
        super(message);
    }
}

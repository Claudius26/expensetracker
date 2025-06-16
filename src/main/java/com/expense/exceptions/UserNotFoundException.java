package com.expense.exceptions;

public class UserNotFoundException extends ExpenseException{
    public UserNotFoundException(String message) {
        super(message);
    }
}

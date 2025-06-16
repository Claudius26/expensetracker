package com.expense.exceptions;

public class UserNotLoggedInException extends ExpenseException{
    public UserNotLoggedInException(String message) {
        super(message);
    }
}

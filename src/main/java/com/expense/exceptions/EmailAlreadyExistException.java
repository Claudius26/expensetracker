package com.expense.exceptions;

public class EmailAlreadyExistException extends ExpenseException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}

package com.expense.exceptions;

public class InvalidCredentialException extends ExpenseException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}

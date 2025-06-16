package com.expense.exceptions;

public class InvalidAmountException extends ExpenseException{
    public InvalidAmountException(String message) {
        super(message);
    }
}

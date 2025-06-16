package com.expense.services;

import com.expense.data.model.Expense;
import com.expense.data.repository.Expenses;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseServiceImplTest {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private Expenses expenses;

    @BeforeEach
    void setUp() {
        expenses.deleteAll();
    }

    @Test
    public void addExpenseIncreasesTheNumberofexpensesInRepo(){
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount(23.89);
        expenseService.addExpense(expenseRequest);
        assertEquals(expenses.count(), 1);

    }

    @Test
    public void addExpenseThrowsExceptionWhenAmountIsLessthanOrZero(){
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount(-3.0);
        assertThrows(InvalidAmountException.class, () ->
                expenseService.addExpense(expenseRequest));
    }

}
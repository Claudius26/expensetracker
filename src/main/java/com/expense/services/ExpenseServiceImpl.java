package com.expense.services;

import com.expense.data.model.Expense;
import com.expense.data.model.User;
import com.expense.data.repository.Expenses;
import com.expense.data.repository.Users;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.response.ExpenseResponse;
import com.expense.exceptions.InvalidAmountException;
import com.expense.exceptions.UserNotFoundException;
import com.expense.exceptions.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.expense.utils.Mapper.isValid;
import static com.expense.utils.Mapper.map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private Expenses expenses;

    @Autowired
    private Users users;

    @Override
    public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {
        Optional<User> user = users.findByEmail(expenseRequest.getUserEmail());
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
        if(!user.get().isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
        if(!isValid(expenseRequest.getAmount()))
            throw new InvalidAmountException("Amount must be greater than 0");
        Expense expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setDate(expenseRequest.getDate());
        expense.setUserId(user.get().getId());
        return map(expenses.save(expense));
    }

    @Override
    public List<ExpenseResponse> viewAllExpenses(String userEmail) {
        Optional<User> user = users.findByEmail(userEmail);
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
        if(!user.get().isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
        List<Expense> expenses1 = expenses.findExpenseByUserId(user.get().getId());
        List<ExpenseResponse> expenseResponses = new ArrayList<>();
        for(Expense expense : expenses1) {
            expenseResponses.add(map(expense));
        }

        return expenseResponses;
    }

    @Override
    public double calculateTotalExpense(String  userEmail) {
        double amount = 0;
        Optional<User> user = users.findByEmail(userEmail);
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
        if(!user.get().isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
        List<Expense> expenses1 = expenses.findExpenseByUserId(user.get().getId());
        for(Expense expense : expenses1) {
            amount +=expense.getAmount();
        }
        return amount;
    }
}

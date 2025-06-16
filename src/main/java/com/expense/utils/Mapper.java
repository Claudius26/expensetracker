package com.expense.utils;

import com.expense.data.model.Expense;
import com.expense.data.model.User;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.requests.UserRequest;
import com.expense.dtos.response.ExpenseResponse;
import com.expense.dtos.response.UserResponse;
import com.expense.exceptions.InvalidAmountException;

public class Mapper {

    public static ExpenseResponse map(Expense expense){
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setDescription(expense.getDescription());
        expenseResponse.setAmount(expense.getAmount());
        expenseResponse.setDate(expense.getDate());
        return expenseResponse;
    }

    public static User map(UserRequest userRequest){
        User user = new User();
        user.setFullName(userRequest.getFirstName()+" "+userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        return user;
    }

    public static UserResponse map(User  user){
        UserResponse userResponse = new UserResponse();
        userResponse.setFullName(user.getFullName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        return userResponse;
    }
    public static boolean isValid(double amount) {
        return amount > 0.0;
    }
}

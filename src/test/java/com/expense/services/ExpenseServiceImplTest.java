package com.expense.services;

import com.expense.data.model.Expense;
import com.expense.data.model.User;
import com.expense.data.repository.Expenses;
import com.expense.data.repository.Users;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.requests.LogOutRequest;
import com.expense.dtos.requests.UserRequest;
import com.expense.dtos.response.ExpenseResponse;
import com.expense.dtos.response.UserResponse;
import com.expense.exceptions.InvalidAmountException;
import com.expense.exceptions.NoExpenseAddedException;
import com.expense.exceptions.UserNotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseServiceImplTest {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private Expenses expenses;

    @Autowired
    private UserService userService;

    @Autowired
    private Users users;

    @BeforeEach
    void setUp() {
        expenses.deleteAll();
        users.deleteAll();

    }

    @Test
    public void addExpenseIncreasesTheNumberofexpensesInRepo(){
        UserResponse userResponse = userService.register(register());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("23.89");
        expenseRequest.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest);
        assertEquals(1, expenses.count());
        ExpenseRequest expenseRequest1 = new ExpenseRequest();
        expenseRequest1.setDescription("This is a test expense");
        expenseRequest1.setDate("23/07/2025");
        expenseRequest1.setAmount("23.89");
        expenseRequest1.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest1);
        assertEquals(expenses.count(), 2);

    }

    @Test
    public void addExpenseThrowsExceptionWhenAmountIsLessthanOrZero(){
        UserResponse userResponse = userService.register(register());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("-3.0");
        expenseRequest.setUserEmail(userResponse.getEmail());
        assertThrows(InvalidAmountException.class, () ->
                expenseService.addExpense(expenseRequest));
    }

    @Test
    public void addExpenseThrowsExceptionWhenUserisNotLoggedIn(){
        UserResponse userResponse = userService.register(register());
        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setCurrentUserEmail(userResponse.getEmail());
        userService.logOut(logOutRequest);
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("23.89");
        expenseRequest.setUserEmail(userResponse.getEmail());
        assertThrows(UserNotLoggedInException.class, () ->{
            expenseService.addExpense(expenseRequest);
        });

    }

    @Test
    public void userCanViewAllTheirAddedExpenses(){
        UserResponse userResponse = userService.register(register());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("23.89");
        expenseRequest.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest);
        ExpenseRequest expenseRequest1 = new ExpenseRequest();
        expenseRequest1.setDescription("This is a test expense");
        expenseRequest1.setDate("23/07/2025");
        expenseRequest1.setAmount("23.89");
        expenseRequest1.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest1);
        ExpenseRequest expenseRequest2 = new ExpenseRequest();
        expenseRequest2.setDescription("This is a test expense");
        expenseRequest2.setDate("23/07/2025");
        expenseRequest2.setAmount("23.89");
        expenseRequest2.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest2);

        UserRequest userRequest = register();
        userRequest.setEmail("claudius@gmail.com");
        userService.register(userRequest);
        ExpenseRequest expenseRequest3 = new ExpenseRequest();
        expenseRequest3.setDescription("This is a test expense");
        expenseRequest3.setDate("23/07/2025");
        expenseRequest3.setAmount("23.89");
        expenseRequest3.setUserEmail("claudius@gmail.com");
        expenseService.addExpense(expenseRequest3);
        assertEquals(4, expenses.count());
        List<ExpenseResponse> expense1 = expenseService.viewAllExpenses(userResponse.getEmail());
        assertEquals(3, expense1.size());

    }

    @Test
    public void userCanCalculateTotalExpenses(){
        UserResponse userResponse = userService.register(register());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("20.00");
        expenseRequest.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest);
        ExpenseRequest expenseRequest1 = new ExpenseRequest();
        expenseRequest1.setDescription("This is a test expense");
        expenseRequest1.setDate("23/07/2025");
        expenseRequest1.setAmount("20.00");
        expenseRequest1.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest1);
        ExpenseRequest expenseRequest2 = new ExpenseRequest();
        expenseRequest2.setDescription("This is a test expense");
        expenseRequest2.setDate("23/07/2025");
        expenseRequest2.setAmount("20.00");
        expenseRequest2.setUserEmail(userResponse.getEmail());
        expenseService.addExpense(expenseRequest2);
        double amount = expenseService.calculateTotalExpense(expenseRequest1.getUserEmail());
        assertEquals(60.00, amount);
    }

    @Test
    public void userCanCalculateTotalExpensesThrowsExceptionWhenNothingIsAdded(){
        UserResponse userResponse = userService.register(register());
        assertThrows(NoExpenseAddedException.class, () ->{
            expenseService.calculateTotalExpense(userResponse.getEmail());
        });
    }

    @Test
    public void userCanViewExpensesThrowsExceptionWhenNoExpenseIsAdded(){
        UserResponse userResponse = userService.register(register());
        assertThrows(NoExpenseAddedException.class, () ->{
            expenseService.viewAllExpenses(userResponse.getEmail());
        });
    }

    @Test
    public void userCannotInputAStringInPlaceOfAmountWhenAddingAnExpense(){
        UserResponse userResponse = userService.register(register());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setDescription("This is a test expense");
        expenseRequest.setDate("23/07/2025");
        expenseRequest.setAmount("abc");
        expenseRequest.setUserEmail(userResponse.getEmail());
        assertThrows(InvalidAmountException.class, () ->{
            expenseService.addExpense(expenseRequest);
        });
    }


    private UserRequest register(){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("John");
        userRequest.setLastName("Doe");
        userRequest.setEmail("john.doe@gmail.com");
        userRequest.setPassword("12345");
        userRequest.setAddress("kari mu");
        userRequest.setPhoneNumber("1234567890");
        return userRequest;
    }


}
package com.expense.controller;

import com.expense.data.model.Expense;
import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.response.ApiResponse;
import com.expense.dtos.response.ExpenseResponse;
import com.expense.exceptions.ExpenseException;
import com.expense.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("user/addexpense")
    @ResponseBody
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequest expenseRequest) {

        try{
            ExpenseResponse expenseResponse = expenseService.addExpense(expenseRequest);
            return new ResponseEntity<>(new ApiResponse(expenseResponse, true), HttpStatus.CREATED);
        }catch(ExpenseException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("user/viewAllExpenses")
    public ResponseEntity<?> viewAllExpenses(@RequestParam("userEmail") String userEmail) {
        try{
            List<ExpenseResponse> expenseResponse = expenseService.viewAllExpenses(userEmail);
            return new ResponseEntity<>(new ApiResponse(expenseResponse, true), HttpStatus.OK);

        }catch(ExpenseException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/calculateAllExpenses")
    public ResponseEntity<?> calculateAllExpenses(@RequestParam("userEmail") String userEmail) {
        try{
            double calculatedExpenses = expenseService.calculateTotalExpense(userEmail);
            return new ResponseEntity<>(new ApiResponse(calculatedExpenses, true), HttpStatus.OK);
        }catch(ExpenseException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}

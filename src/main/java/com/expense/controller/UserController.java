package com.expense.controller;

import com.expense.dtos.requests.ExpenseRequest;
import com.expense.dtos.requests.LogOutRequest;
import com.expense.dtos.requests.LoginRequest;
import com.expense.dtos.requests.UserRequest;
import com.expense.dtos.response.ApiResponse;
import com.expense.dtos.response.LoginResponse;
import com.expense.dtos.response.UserResponse;
import com.expense.exceptions.ExpenseException;
import com.expense.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("user/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.register(userRequest);
            return new ResponseEntity<>(new ApiResponse(userResponse, true), HttpStatus.CREATED);
        }catch(ExpenseException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            LoginResponse response = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (ExpenseException exception) {
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/user/logOut")
    public ResponseEntity<?> logout(@RequestBody LogOutRequest logOutRequest) {
        try{
            userService.logOut(logOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, true), HttpStatus.OK);
        }catch(ExpenseException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}

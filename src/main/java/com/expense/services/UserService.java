package com.expense.services;

import com.expense.dtos.requests.LogOutRequest;
import com.expense.dtos.requests.LoginRequest;
import com.expense.dtos.requests.UserRequest;
import com.expense.dtos.response.LoginResponse;
import com.expense.dtos.response.UserResponse;

public interface UserService {
    UserResponse register(UserRequest request);
    LoginResponse login(LoginRequest loginRequest);
    void logOut(LogOutRequest LogOutRequest);
}

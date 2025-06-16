package com.expense.services;

import com.expense.data.model.User;
import com.expense.data.repository.Users;
import com.expense.dtos.requests.LogOutRequest;
import com.expense.dtos.requests.LoginRequest;
import com.expense.dtos.requests.UserRequest;
import com.expense.dtos.response.LoginResponse;
import com.expense.dtos.response.UserResponse;
import com.expense.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.expense.utils.Mapper.map;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private Users users;

    @Override
    public UserResponse register(UserRequest request) {
        if(request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ||
                request.getEmail().isEmpty() ||
                request.getPhoneNumber().isEmpty() ||
                request.getPassword().isEmpty())
        {
            throw new EmptyDetailsException("Details must be filled");
        }
        Optional<User> user = users.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new EmailAlreadyExistException("Email already exist");
        }
        return map(users.save(map(request)));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if(loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()){
            throw new EmptyDetailsException("Details must be filled");
        }
        Optional<User> user = users.findByEmail(loginRequest.getEmail());
        if(user.isEmpty() || !user.get().getPassword().equals(loginRequest.getPassword())){
            throw new InvalidCredentialException("Invalid credentials");
        }
        if(user.get().isLoggedIn()){
            throw new UserAllReadyLoggedInException("User is already logged in");
        }
        user.get().setLoggedIn(true);
        users.save(user.get());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Success");
        return loginResponse;
    }

    @Override
    public void logOut(LogOutRequest logOutRequest) {
        Optional<User> user = users.findByEmail(logOutRequest.getCurrentUserEmail());
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        if(!user.get().isLoggedIn()){
            throw new UserNotLoggedInException("User not logged in");
        }
        user.get().setLoggedIn(false);
        users.save(user.get());

    }
}

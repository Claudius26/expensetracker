package com.expense.dtos.response;

import lombok.Data;

@Data
public class UserResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
}

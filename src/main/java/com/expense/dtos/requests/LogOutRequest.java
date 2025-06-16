package com.expense.dtos.requests;

import lombok.Data;

@Data
public class LogOutRequest {
    private String currentUserEmail;
}

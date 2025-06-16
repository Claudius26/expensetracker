package com.expense.dtos.response;

import lombok.Data;

@Data
public class ExpenseResponse {
    private String description;
    private Double amount;
    private String date;
}

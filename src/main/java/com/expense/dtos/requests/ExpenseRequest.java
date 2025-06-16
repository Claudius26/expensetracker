package com.expense.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ExpenseRequest {
    private String description;
    private double amount;
    private String date;
}

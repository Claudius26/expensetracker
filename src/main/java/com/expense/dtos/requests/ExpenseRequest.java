package com.expense.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ExpenseRequest {
    private String description;
    private String amount;
    private String date;
    private String userEmail;
}

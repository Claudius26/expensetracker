package com.expense.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Expense {
    @Id
    private String id;
    private String description;
    private double amount;
    private String date;
    private String userId;

}

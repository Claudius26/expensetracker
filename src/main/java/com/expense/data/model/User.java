package com.expense.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private boolean isLoggedIn = true;

}

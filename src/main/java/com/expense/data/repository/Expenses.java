package com.expense.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Expenses extends MongoRepository<Expenses, String> {
}

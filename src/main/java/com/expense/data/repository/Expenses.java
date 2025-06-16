package com.expense.data.repository;

import com.expense.data.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Expenses extends MongoRepository<Expense, String> {
}

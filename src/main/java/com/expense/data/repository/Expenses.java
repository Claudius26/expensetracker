package com.expense.data.repository;

import com.expense.data.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Expenses extends MongoRepository<Expense, String> {
    List<Expense> findExpenseByUserId(String id);
}

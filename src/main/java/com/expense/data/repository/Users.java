package com.expense.data.repository;

import com.expense.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Users extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}

package com.getircase.readingisgood.adapters.persistance.repository;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUsername(String usernameOrEmail);
}

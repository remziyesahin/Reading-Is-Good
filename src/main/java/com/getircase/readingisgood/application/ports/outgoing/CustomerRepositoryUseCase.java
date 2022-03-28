package com.getircase.readingisgood.application.ports.outgoing;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;

import java.util.Optional;

public interface CustomerRepositoryUseCase {
    void saveCustomer(Customer customer);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsername(String username);
}

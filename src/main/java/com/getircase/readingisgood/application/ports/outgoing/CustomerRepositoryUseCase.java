package com.getircase.readingisgood.application.ports.outgoing;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;

public interface CustomerRepositoryUseCase {
    void saveCustomer(Customer customer);
}

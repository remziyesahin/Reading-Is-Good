package com.getircase.readingisgood.application.ports.incoming;

import lombok.AllArgsConstructor;
import lombok.Data;

public interface CustomerUseCase {
    void createCustomer(CustomerCreationCommand customerCreationCommand);

    @Data
    @AllArgsConstructor
    public class CustomerCreationCommand {
        private String name;
        private String lastName;
        private String username;
        private String email;
        private String password;
    }

}

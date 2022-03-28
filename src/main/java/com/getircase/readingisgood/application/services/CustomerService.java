package com.getircase.readingisgood.application.services;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.application.domain.common.UserRole;
import com.getircase.readingisgood.application.domain.exception.UserAlreadyExistException;
import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase;
import com.getircase.readingisgood.application.ports.outgoing.CustomerRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {
    private final CustomerRepositoryUseCase customerRepositoryUseCase;

    private final PasswordEncoder encoder;
    @Override
    public void createCustomer(CustomerCreationCommand customerCreationCommand) {
        log.info("createCustomer---->");
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        //Confirm email address, it must be unique
        confirmEmail(customerCreationCommand.getEmail());

        Customer customer = Customer.builder()
                .name(customerCreationCommand.getName())
                .lastName(customerCreationCommand.getLastName())
                .username(customerCreationCommand.getUsername())
                .email(customerCreationCommand.getEmail())
                .password(encodePassword(customerCreationCommand.getPassword()))
                .userRole(roles)
                .build();

        customerRepositoryUseCase.saveCustomer(customer);

        log.info(customer.getUsername() + " created as a new customer");
        log.info("<----createCustomer");
    }

    private void confirmEmail(String email) {
        Optional<Customer> customer = customerRepositoryUseCase.findByEmail(email);
        if (customer.isPresent()) throw new UserAlreadyExistException("Customer with " + email + " already exist!");
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

}

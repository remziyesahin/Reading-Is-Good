package com.getircase.readingisgood.application.services;

import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.application.domain.exception.BookRetailObjectNotFoundException;
import com.getircase.readingisgood.application.ports.outgoing.CustomerRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerRepositoryUseCase customerRepositoryUseCase;

    public Customer getCurrentUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final String username;

        if (principal instanceof UserDetails ) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<Customer> customer = customerRepositoryUseCase.findByUsername(username);

        if (customer.isEmpty()) {
            throw new BookRetailObjectNotFoundException("Customer with " + username + " not found!");
        }

        return customer.get();
    }

}

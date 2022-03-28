package com.getircase.readingisgood.adapters.api.controller;

import com.getircase.readingisgood.adapters.api.request.CustomerCreationRequest;
import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping("/registration")
    public void createCustomer(@Valid @RequestBody CustomerCreationRequest customerRequest) {
        customerUseCase.createCustomer(customerRequest.toCommand());
    }

}

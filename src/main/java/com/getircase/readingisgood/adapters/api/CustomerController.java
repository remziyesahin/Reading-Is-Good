package com.getircase.readingisgood.adapters.api;

import com.getircase.readingisgood.adapters.api.request.CustomerCreationRequest;
import com.getircase.readingisgood.adapters.api.request.LoginRequest;
import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping("/registration")
    public void createCustomer(@RequestBody CustomerCreationRequest customerRequest) {
        customerUseCase.createCustomer(customerRequest.toCommand());
    }

}

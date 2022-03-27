package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase.CustomerCreationCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreationRequest {

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public CustomerCreationCommand toCommand(){
        return new CustomerCreationCommand(this.name, this.lastName, this.username, this.email, this.password);
    }
}

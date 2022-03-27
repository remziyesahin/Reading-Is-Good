package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.CustomerUseCase.CustomerCreationCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreationRequest {

    @NotNull
    private String name;
    private String lastName;

    @NotNull
    @Size(min = 8, max = 255)
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public CustomerCreationCommand toCommand(){
        return new CustomerCreationCommand(this.name, this.lastName, this.username, this.email, this.password);
    }
}

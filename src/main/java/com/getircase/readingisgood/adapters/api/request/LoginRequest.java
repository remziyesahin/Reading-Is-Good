package com.getircase.readingisgood.adapters.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Username must be filled!")
    private String username;
    @NotBlank(message = "Password must be filled!")
    private String password;
}

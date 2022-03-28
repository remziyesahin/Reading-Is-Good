package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;

}

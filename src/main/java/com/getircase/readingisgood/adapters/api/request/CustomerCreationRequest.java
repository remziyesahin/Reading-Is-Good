package com.getircase.readingisgood.adapters.api.request;

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

}

package com.getircase.readingisgood.adapters.persistance.entity;

import com.getircase.readingisgood.application.domain.common.UserRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("customer")
@Builder
@Data
public class Customer extends BaseEntity{

    private String name;
    private String lastName;
    private String username;

    @Indexed(unique = true)
    private String email;
    private String password;
    private Set<UserRole> userRole;

}

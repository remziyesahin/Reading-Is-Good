package com.getircase.readingisgood.adapters.persistance.entity;

import com.getircase.readingisgood.application.domain.common.UserRole;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collation = "customer")
@Builder
public class Customer extends BaseEntity{

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;

    @Builder.Default
    private UserRole userRole = UserRole.USER;

    @DBRef
    private Set<Order> orders = new HashSet<>();

}

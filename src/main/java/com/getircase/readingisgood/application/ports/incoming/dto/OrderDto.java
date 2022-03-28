package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderDto {
    @NotNull(message = "Book Id should be filled!")
    private String bookId;

    @Min(1)
    private Integer quantity;
}

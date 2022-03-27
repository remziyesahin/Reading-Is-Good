package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class OrderDto {
    private String bookId;

    @Min(1)
    private Integer quantity;
}

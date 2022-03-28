package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private String title;
    private BigDecimal unitPrice;
    private int quantity;
}

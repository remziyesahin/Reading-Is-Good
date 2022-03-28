package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderStatisticsDto {
    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchaseAmount;
}

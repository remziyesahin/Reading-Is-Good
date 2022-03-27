package com.getircase.readingisgood.application.ports.incoming.dto;

import com.getircase.readingisgood.application.domain.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class OrderResponseDto {
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String orderTrackingNumber;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItemResponseDto> orderItems;
}

package com.getircase.readingisgood.adapters.persistance.entity;

import com.getircase.readingisgood.application.domain.common.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;


@Document("order")
@Data
@Builder
public class Order extends BaseEntity{
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String orderTrackingNumber;
    private OrderStatus status;
    private String customerId;
    private Set<OrderItem> orderItems;

    @Version
    private Long version;
}

package com.getircase.readingisgood.adapters.persistance.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("orderItem")
@Data
@Builder
public class OrderItem extends BaseEntity {
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;

    @DBRef
    private Book book;

    @DBRef
    private Order order;

}









package com.getircase.readingisgood.application.ports.outgoing;

import com.getircase.readingisgood.adapters.persistance.entity.OrderItem;

import java.util.List;

public interface OrderItemRepositoryUseCase {
    void saveOrderItems(List<OrderItem> orderItems);
}

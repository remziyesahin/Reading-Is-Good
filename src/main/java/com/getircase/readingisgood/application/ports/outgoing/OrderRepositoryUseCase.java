package com.getircase.readingisgood.application.ports.outgoing;

import com.getircase.readingisgood.adapters.persistance.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepositoryUseCase {
    void saveOrder(Order order);

    Optional<Order> findOrder(String orderId);
    Page<Order> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Order> findByCustomerIdAndCreatedDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}

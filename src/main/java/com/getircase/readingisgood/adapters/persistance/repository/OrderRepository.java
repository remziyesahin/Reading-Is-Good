package com.getircase.readingisgood.adapters.persistance.repository;

import com.getircase.readingisgood.adapters.persistance.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    Page<Order> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    List<Order> findByCustomerIdAndCreatedDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}

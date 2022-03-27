package com.getircase.readingisgood.application.ports.incoming;

import com.getircase.readingisgood.application.ports.incoming.dto.OrderDto;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderUseCase {
    void createOrder(OrderCreationCommand orderCreationCommand);
    OrderResponseDto getOrder(OrderCommand orderCommand);
    Page<OrderResponseDto> searchOrders(OrderSearchCommand orderSearchCommand, Pageable pageable);

    @Data
    @AllArgsConstructor
    class OrderCreationCommand {
        private List<OrderDto> orders;
    }

    @Data
    @AllArgsConstructor
    class OrderCommand {
        private String orderId;
    }

    @Data
    @AllArgsConstructor
    class OrderSearchCommand {
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }
}

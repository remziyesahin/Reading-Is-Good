package com.getircase.readingisgood.adapters.api.controller;

import com.getircase.readingisgood.adapters.api.request.OrderCreationRequest;
import com.getircase.readingisgood.adapters.api.request.OrderRequest;
import com.getircase.readingisgood.adapters.api.request.OrderSearchRequest;
import com.getircase.readingisgood.application.ports.incoming.OrderUseCase;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    public void createOrder(@RequestBody OrderCreationRequest orderRequest) {
        orderUseCase.createOrder(orderRequest.toCommand());
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable String orderId) {
        OrderRequest orderRequest = new OrderRequest(orderId);
        return orderUseCase.getOrder(orderRequest.toCommand());
    }

    @GetMapping("/search")
    public Page<OrderResponseDto> searchOrders(@RequestBody @Valid OrderSearchRequest orderSearchRequest, Pageable pageable) {
        return orderUseCase.searchOrders(orderSearchRequest.toCommand(), pageable);
    }

}

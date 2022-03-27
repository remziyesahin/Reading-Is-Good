package com.getircase.readingisgood.application.services;

import com.getircase.readingisgood.adapters.persistance.entity.Book;
import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.adapters.persistance.entity.Order;
import com.getircase.readingisgood.adapters.persistance.entity.OrderItem;
import com.getircase.readingisgood.application.domain.common.OrderStatus;
import com.getircase.readingisgood.application.domain.exception.BookRetailObjectNotFoundException;
import com.getircase.readingisgood.application.ports.incoming.OrderUseCase;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderItemResponseDto;
import com.getircase.readingisgood.application.ports.incoming.dto.OrderResponseDto;
import com.getircase.readingisgood.application.ports.outgoing.BookRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.OrderItemRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.OrderRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final OrderRepositoryUseCase orderRepositoryUseCase;
    private final OrderItemRepositoryUseCase orderItemRepositoryUseCase;
    private final BookRepositoryUseCase bookRepositoryUseCase;
    private final AuthenticationService authenticationService;

    @Override
    public void createOrder(OrderCreationCommand orderCreationCommand) {
        log.info("createOrder---->");
        Customer currentUser = authenticationService.getCurrentUser();
        log.info("Customer " + currentUser.getUsername() + " will create order!");
        //Prepare order and order items
        Order order = prepareOrder(orderCreationCommand, currentUser.getId());

        orderItemRepositoryUseCase.saveOrderItems(order.getOrderItems().stream().toList());

        orderRepositoryUseCase.saveOrder(order);
        log.info(currentUser.getUsername() + " created new order!");
        log.info("<----createOrder");
    }

    private Order prepareOrder(OrderCreationCommand orderCreationCommand, String customerId) {

        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        Set<OrderItem> orderItems = new HashSet<>();
        orderCreationCommand.getOrders().forEach(o -> {
            Optional<Book> book = bookRepositoryUseCase.findByBookId(o.getBookId());
            if (book.isEmpty()) throw new BookRetailObjectNotFoundException("Book not exist for " + o.getBookId());

            if (o.getQuantity() > book.get().getUnitsInStock()) {
                throw new BookRetailObjectNotFoundException("Insufficient stock for "+ book.get().getTitle() + " book.");
            }
            OrderItem orderItem = OrderItem.builder()
                    .quantity(o.getQuantity())
                    .unitPrice(book.get().getUnitPrice())
                    .book(book.get()).build();
            BigDecimal orderItemPrice = book.get().getUnitPrice().multiply(new BigDecimal(o.getQuantity()));
            totalPrice[0] = totalPrice[0].add(orderItemPrice);
            orderItems.add(orderItem);

            //Decrease book stock
            updateBookStock(book.get(), o.getQuantity());

        });
        return Order.builder()
                .totalQuantity(orderCreationCommand.getOrders().size())
                .totalPrice(totalPrice[0])
                .orderTrackingNumber(generateOrderTrackingNumber())
                .customerId(customerId)
                .status(OrderStatus.ORDERED)
                .orderItems(orderItems).build();
    }

    private void updateBookStock(Book book, int orderQuantity) {
        book.setUnitsInStock(book.getUnitsInStock()-orderQuantity);
        bookRepositoryUseCase.updateBook(book);
    }

    @Override
    public OrderResponseDto getOrder(OrderCommand orderCommand) {
        log.info("getOrder---->");

        Optional<Order> order = orderRepositoryUseCase.findOrder(orderCommand.getOrderId());

        if (order.isPresent()){
            log.info("<----getOrder");
            return orderToOrderResponseDto(order.get());
        }else {
            throw new BookRetailObjectNotFoundException("Order with " + orderCommand.getOrderId() + " order Id not found!");
        }

    }


    @Override
    public Page<OrderResponseDto> searchOrders(OrderSearchCommand orderSearchCommand, Pageable pageable) {
        log.info("searchOrders---->");

        Page<Order> orders = orderRepositoryUseCase.findByCreatedDateBetween(orderSearchCommand.getStartDate(), orderSearchCommand.getEndDate(), pageable);

        List<OrderResponseDto> orderList = new ArrayList<>();
        orders.forEach(order -> {
            OrderResponseDto orderResponseDto = orderToOrderResponseDto(order);
            orderList.add(orderResponseDto);
        });
        log.info("Orders are searched between " + orderSearchCommand.getStartDate() + " and " + orderSearchCommand.getEndDate());
        log.info("<----searchOrders");

        return new PageImpl<>(orderList, pageable, orders.getTotalElements());
    }


    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    public OrderResponseDto orderToOrderResponseDto(Order order) {

        OrderResponseDto orderResponseDto = getOrderResponseDto(order);
        List<OrderItemResponseDto> orderItemResponseDtos = getOrderItemResponseDtos(order);
        orderResponseDto.setOrderItems(orderItemResponseDtos);

        return orderResponseDto;
    }

    private List<OrderItemResponseDto> getOrderItemResponseDtos(Order order) {
        List<OrderItemResponseDto> orderItemResponseDtos = new ArrayList<>();
        order.getOrderItems().forEach(i -> {
            OrderItemResponseDto  orderItemResponseDto = OrderItemResponseDto.builder()
                    .quantity(i.getQuantity())
                    .title(i.getBook().getTitle())
                    .unitPrice(i.getUnitPrice()).build();
            orderItemResponseDtos.add(orderItemResponseDto);
        });
        return orderItemResponseDtos;
    }

    private OrderResponseDto getOrderResponseDto(Order order) {
        return OrderResponseDto.builder()
                    .totalPrice(order.getTotalPrice())
                    .totalQuantity(order.getTotalQuantity())
                    .orderTrackingNumber(order.getOrderTrackingNumber())
                    .status(order.getStatus()).build();
    }
}

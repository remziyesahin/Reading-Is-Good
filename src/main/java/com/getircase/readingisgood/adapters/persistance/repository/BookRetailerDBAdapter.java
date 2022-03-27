package com.getircase.readingisgood.adapters.persistance.repository;

import com.getircase.readingisgood.adapters.persistance.entity.Book;
import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.adapters.persistance.entity.Order;
import com.getircase.readingisgood.adapters.persistance.entity.OrderItem;
import com.getircase.readingisgood.application.ports.outgoing.BookRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.CustomerRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.OrderItemRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.OrderRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookRetailerDBAdapter implements CustomerRepositoryUseCase, BookRepositoryUseCase, OrderRepositoryUseCase, OrderItemRepositoryUseCase {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findByBookId(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrder(String orderId) {
        return  orderRepository.findById(orderId);
    }

    @Override
    public Page<Order> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return orderRepository.findByCreatedDateBetween(startDate, endDate, pageable);
    }

    @Override
    public List<Order> findByCustomerIdAndCreatedDateBetween(String customerId, LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByCustomerIdAndCreatedDateBetween(customerId, startDate, endDate);
    }

    @Override
    public void saveOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}

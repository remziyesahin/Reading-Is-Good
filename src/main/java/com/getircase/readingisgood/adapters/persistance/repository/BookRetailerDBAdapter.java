package com.getircase.readingisgood.adapters.persistance.repository;

import com.getircase.readingisgood.adapters.persistance.entity.Book;
import com.getircase.readingisgood.adapters.persistance.entity.Customer;
import com.getircase.readingisgood.adapters.persistance.entity.Order;
import com.getircase.readingisgood.application.ports.outgoing.BookRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.CustomerRepositoryUseCase;
import com.getircase.readingisgood.application.ports.outgoing.OrderRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookRetailerDBAdapter implements CustomerRepositoryUseCase, BookRepositoryUseCase, OrderRepositoryUseCase {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

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
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}

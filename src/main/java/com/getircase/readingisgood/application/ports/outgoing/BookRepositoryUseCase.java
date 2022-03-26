package com.getircase.readingisgood.application.ports.outgoing;

import com.getircase.readingisgood.adapters.persistance.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryUseCase {
    void saveBooks(List<Book> books);
    void updateBook(Book book);
    Optional<Book> findByBookId(String bookId);
}

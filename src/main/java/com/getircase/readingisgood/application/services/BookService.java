package com.getircase.readingisgood.application.services;

import com.getircase.readingisgood.adapters.api.request.BookUpdateRequest;
import com.getircase.readingisgood.adapters.persistance.entity.Book;
import com.getircase.readingisgood.application.domain.exception.BookRetailObjectNotFoundException;
import com.getircase.readingisgood.application.ports.incoming.BookUseCase;
import com.getircase.readingisgood.application.ports.outgoing.BookRepositoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService implements BookUseCase {
    private final BookRepositoryUseCase bookRepositoryUseCase;

    @Override
    public void createBook(BookCreationCommand bookCreationCommand) {
        log.info("createBook---->");
        List<Book> books = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        bookCreationCommand.getBooks().forEach(b -> {
            Book book = modelMapper.map(b, Book.class);
            books.add(book);
        });

        bookRepositoryUseCase.saveBooks(books);
        log.info("<----createBook");
    }

    @Override
    public void updateBook(BookUpdateCommand bookUpdateCommand) {
        log.info("updateBook---->");

        Optional<Book> book = bookRepositoryUseCase.findByBookId(bookUpdateCommand.getBookId());
        book.ifPresentOrElse(
                b -> {
                    b.setUnitsInStock(bookUpdateCommand.getUnitsInStock());
                    bookRepositoryUseCase.updateBook(b);
                    log.info("Stock number of book with" + bookUpdateCommand.getBookId() + " book Id is updated as " + bookUpdateCommand.getUnitsInStock() + " !");
                },
                () -> {
                    throw new BookRetailObjectNotFoundException("Book with " + bookUpdateCommand.getBookId() + " book Id not found!");
                });

        log.info("<----updateBook");
    }
}

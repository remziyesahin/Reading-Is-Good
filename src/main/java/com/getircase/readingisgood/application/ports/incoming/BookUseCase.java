package com.getircase.readingisgood.application.ports.incoming;

import com.getircase.readingisgood.application.ports.incoming.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public interface BookUseCase {
    void createBook(BookCreationCommand bookCommand);
    void updateBook(BookUpdateCommand bookUpdateCommand);

    @Data
    @AllArgsConstructor
    public class BookCreationCommand {
        private List<BookDto> books;
    }

    @Data
    @AllArgsConstructor
    public class BookUpdateCommand {
        private String bookId;
        private int unitsInStock;
    }
}

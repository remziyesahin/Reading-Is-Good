package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.BookUseCase.BookCreationCommand;
import com.getircase.readingisgood.application.ports.incoming.dto.BookDto;
import lombok.Data;

import java.util.List;

@Data
public class BookCreationRequest {
    private List<BookDto> books;

    public BookCreationCommand toCommand(){
        return new BookCreationCommand(this.books);
    }

}

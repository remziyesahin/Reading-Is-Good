package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.BookUseCase;
import com.getircase.readingisgood.application.ports.incoming.BookUseCase.BookUpdateCommand;
import lombok.Data;

@Data
public class BookUpdateRequest {
    private int unitsInStock;

    public BookUpdateCommand toCommand(String bookId){
        return new BookUseCase.BookUpdateCommand(bookId, this.unitsInStock);
    }
}

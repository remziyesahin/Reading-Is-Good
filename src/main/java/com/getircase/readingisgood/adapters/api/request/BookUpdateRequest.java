package com.getircase.readingisgood.adapters.api.request;

import com.getircase.readingisgood.application.ports.incoming.BookUseCase;
import com.getircase.readingisgood.application.ports.incoming.BookUseCase.BookUpdateCommand;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BookUpdateRequest {
    @NotBlank(message = "Number of stock must be specified!")
    private int unitsInStock;

    public BookUpdateCommand toCommand(String bookId){
        return new BookUseCase.BookUpdateCommand(bookId, this.unitsInStock);
    }
}

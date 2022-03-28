package com.getircase.readingisgood.adapters.api.controller;

import com.getircase.readingisgood.adapters.api.request.BookCreationRequest;
import com.getircase.readingisgood.adapters.api.request.BookUpdateRequest;
import com.getircase.readingisgood.application.ports.incoming.BookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@Validated
@RequiredArgsConstructor
public class BookController {

    private final BookUseCase bookUseCase;

    @PostMapping
    public void createBook(@Valid @RequestBody BookCreationRequest bookCreationRequest) {
        bookUseCase.createBook(bookCreationRequest.toCommand());
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable String bookId, @Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        bookUseCase.updateBook(bookUpdateRequest.toCommand(bookId));
    }
}

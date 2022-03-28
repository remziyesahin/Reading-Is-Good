package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    private String author;
    private String lastEdition;
    private int year;
    private String publisher;
    private String isbn;
    @NotNull(message = "Price should be filled!")
    private BigDecimal unitPrice;
    @NotNull(message = "Stock should be filled!")
    private int unitsInStock;
}

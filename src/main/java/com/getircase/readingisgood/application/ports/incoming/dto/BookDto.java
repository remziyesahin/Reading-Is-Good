package com.getircase.readingisgood.application.ports.incoming.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String lastEdition;
    private int year;
    private String publisher;
    private String isbn;
    private BigDecimal unitPrice;
    private int unitsInStock;
}
